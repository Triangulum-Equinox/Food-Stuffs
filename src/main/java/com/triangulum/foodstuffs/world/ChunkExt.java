package com.triangulum.foodstuffs.world;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.triangulum.foodstuffs.network.ServerPacketHandler;

import codechicken.lib.world.ChunkExtension;
import codechicken.lib.world.WorldExtension;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class ChunkExt extends ChunkExtension
{
    
    private Map<BlockPos, Crop> cropMap;
    private PriorityQueue<Crop> cropGrowthQueue;

    public ChunkExt(Chunk chunk, WorldExtension world)
    {
        super(chunk, world);
        cropMap = new HashMap<BlockPos, Crop>();
        cropGrowthQueue = new PriorityQueue<Crop>(new CropComparator());
    }
    
    public void tick(int worldTotalTicks)
    {
        while(cropGrowthQueue.peek().getEndWorldTick() - worldTotalTicks <= 0)
        {
            Crop crop = cropGrowthQueue.poll();
            
            crop.nextGrowthStage();
            ServerPacketHandler.sendCropWatchPacket(crop);
            cropGrowthQueue.offer(crop);
        }
    }
    
    public void addCrop(Crop crop)
    {
        cropMap.put(crop.getBlockPos(), crop);
        cropGrowthQueue.offer(crop);
        
        ServerPacketHandler.sendCropWatchPacket(crop);
    }
    
    public void removeCrop(BlockPos pos)
    {
        Crop crop = cropMap.get(pos);
        
        if(crop == null)
            return;
        
        cropGrowthQueue.remove(crop);
        cropMap.remove(pos);
        
        ServerPacketHandler.sendCropUnWatchPacket(world, pos);
    }
    
    public Crop getCrop(BlockPos pos)
    {
        return cropMap.get(pos);
    }
    
    public void onWatchPlayer(EntityPlayerMP player)
    {
        ServerPacketHandler.sendChunkWatchPacket(cropMap, player);
    }
    
    public void onUnWatchPlayer(EntityPlayerMP player)
    {
        //ServerPacketHandler.sendChunkUnWatchPacket(coord, player);
    }
    
    public void loadData(NBTTagCompound data) 
    {
        NBTTagList crops = data.getTagList("Crops", 10);
        for(int i = 0; i < crops.tagCount(); i++)
        {
            NBTTagCompound cropData = crops.getCompoundTagAt(i);
            WorldExt worldExt = (WorldExt) world;
            Crop crop = new Crop(worldExt);
            
            crop.load(cropData);
            
            worldExt.addCrop(crop);
        }
    }

    public void saveData(NBTTagCompound tag) 
    {
        NBTTagList crops = new NBTTagList();
        for(BlockPos cropPos : cropMap.keySet())
        {
            NBTTagCompound cropData = new NBTTagCompound();
            Crop crop = cropMap.get(cropPos);
            
            crop.save(cropData);
            crops.appendTag(cropData);
        }
        
        tag.setTag("Crops", crops);
    }
    
    private static class CropComparator implements Comparator<Crop>
    {

        @Override
        public int compare(Crop crop, Crop otherCrop)
        {
            return crop.getEndWorldTick() - otherCrop.getEndWorldTick();
        }

    }

}
