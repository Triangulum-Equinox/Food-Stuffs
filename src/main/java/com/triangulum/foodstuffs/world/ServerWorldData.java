package com.triangulum.foodstuffs.world;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.triangulum.foodstuffs.crops.Crop;
import com.triangulum.foodstuffs.crops.CropComparator;
import com.triangulum.foodstuffs.crops.ServerCrop;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ServerWorldData implements IWorldData
{
    
    private World world;
    
    private int worldTotalTicks;
    
    private Map<BlockPos, ServerCrop> cropMap;
    private PriorityQueue<ServerCrop> cropGrowthQueue;
    
    public ServerWorldData(World worldIn)
    {
        world = worldIn;
    }

    public void tick()
    {
        worldTotalTicks++;
        
        while(cropGrowthQueue.peek().getEndWorldTick() - worldTotalTicks <= 0)
        {
            ServerCrop plant = cropGrowthQueue.poll();
            
            plant.nextGrowthStage();
            
            cropGrowthQueue.offer(plant);
        }
    }
    
    public void addCrop(Crop crop)
    {
        ServerCrop serverCrop = (ServerCrop) crop;
        
        cropMap.put(serverCrop.getBlockPos(), serverCrop);
        cropGrowthQueue.offer(serverCrop);
    }
    
    public void removeCrop(BlockPos pos)
    {
        Crop crop = cropMap.get(pos);
        
        if(crop == null)
            return;
        
        cropGrowthQueue.remove(crop);
        cropMap.remove(pos);
    }
    
    public Crop getCrop(BlockPos pos)
    {
        return cropMap.get(pos);
    }
    
    public void load(NBTTagCompound worldData)
    {
        worldTotalTicks = worldData.getInteger("worldTotalTicks");
        
        CropComparator comparator = new CropComparator();
        
        cropMap = new HashMap<BlockPos, ServerCrop>();
        cropGrowthQueue = new PriorityQueue<ServerCrop>(comparator);
        
        NBTTagList plants = worldData.getTagList("plants", 10);
        for(int i = 0; i < plants.tagCount(); i++)
        {
            NBTTagCompound plantData = plants.getCompoundTagAt(i);
            ServerCrop plant = new ServerCrop(world, this);
            
            plant.load(plantData);
            
            addCrop(plant);
        }
    }

    public void save(NBTTagCompound worldData)
    {
        worldData.setInteger("worldTotalTicks", worldTotalTicks);
        
        NBTTagList plants = new NBTTagList();
        for(BlockPos cropPos : cropMap.keySet())
        {
            NBTTagCompound plantData = new NBTTagCompound();
            Crop crop = cropMap.get(cropPos);
            
            crop.save(plantData);
            plants.appendTag(plantData);
        }
    }

}
