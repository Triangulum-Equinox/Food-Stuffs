package com.triangulum.foodstuffs.world;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.triangulum.foodstuffs.plants.Crop;
import com.triangulum.foodstuffs.plants.CropComparator;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldData
{
    
    private World world;
    
    private int worldTotalTicks;
    
    private Map<BlockPos, Crop> cropMap;
    private PriorityQueue<Crop> cropGrowthQueue;
    
    public WorldData(World worldIn)
    {
        world = worldIn;
    }

    public void tick()
    {
        worldTotalTicks++;
        
        while(cropGrowthQueue.peek().getEndWorldTick() - worldTotalTicks <= 0)
        {
            Crop plant = cropGrowthQueue.poll();
            
            plant.nextGrowthStage();
            
            cropGrowthQueue.offer(plant);
        }
    }
    
    public void addCrop(Crop plant)
    {
        cropMap.put(plant.getBlockPos(), plant);
        cropGrowthQueue.offer(plant);
    }
    
    public Crop getPlant(BlockPos pos)
    {
        return cropMap.get(pos);
    }
    
    public void load(NBTTagCompound worldData)
    {
        worldTotalTicks = worldData.getInteger("worldTotalTicks");
        
        CropComparator comparator = new CropComparator();
        
        cropMap = new HashMap<BlockPos, Crop>();
        cropGrowthQueue = new PriorityQueue<Crop>(comparator);
        
        NBTTagList plants = worldData.getTagList("plants", 10);
        for(int i = 0; i < plants.tagCount(); i++)
        {
            NBTTagCompound plantData = plants.getCompoundTagAt(i);
            Crop plant = new Crop(world, this);
            
            plant.load(plantData);
            
            addCrop(plant);
        }
    }

    public void save(NBTTagCompound worldData)
    {
        worldData.setInteger("worldTotalTicks", worldTotalTicks);
    }

}
