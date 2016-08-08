package com.triangulum.foodstuffs.world;

import java.io.File;
import java.io.IOException;

import com.triangulum.foodstuffs.FoodStuffs;

import codechicken.lib.world.WorldExtension;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class WorldExt extends WorldExtension
{
    
    private int worldTotalTicks;
    private NBTTagCompound worldData;

    public WorldExt(World world)
    {
        super(world);
    }
    
    public void postTick()
    {
        worldTotalTicks ++;
        
        for(Chunk chunk : chunkMap.keySet())
        {
            ChunkExt chunkExt = (ChunkExt) chunkMap.get(chunk);
            
            chunkExt.tick(worldTotalTicks);
        }
    }
    
    public ChunkExt getChunkExt(BlockPos pos)
    {
        Chunk chunk = world.getChunkFromBlockCoords(pos);
        
        if(!chunkMap.containsKey(chunk))
            return null;
        
        return (ChunkExt) chunkMap.get(chunk);
    }
    
    public void addCrop(Crop crop)
    {
        ChunkExt chunkExt = getChunkExt(crop.getBlockPos());
        
        if(chunkExt == null)
            return;
        
        chunkExt.addCrop(crop);
    }
    
    public void removeCrop(BlockPos pos)
    {
        ChunkExt chunkExt = getChunkExt(pos);
        
        if(chunkExt == null)
            return;
        
        chunkExt.removeCrop(pos);
    }
    
    public Crop getCrop(BlockPos pos)
    {
        ChunkExt chunkExt = getChunkExt(pos);
        
        if(chunkExt == null)
            return null;
        
        return chunkExt.getCrop(pos);
    }

    public void load()
    {
        WorldServer world = (WorldServer) this.world;

        File dimensionFile = new File(world.getChunkSaveLocation(), FoodStuffs.MOD_ID + "_world.dat");
        if(dimensionFile.exists() && dimensionFile.isFile())
        {
            try
            {
                worldData = CompressedStreamTools.read(dimensionFile);

                worldTotalTicks = worldData.getInteger("worldTotalTicks");
            }
            catch(IOException e) {}
        }
    }
    
    public void save()
    {
        if(worldData == null)
            worldData = new NBTTagCompound();
        
        worldData.setInteger("worldTotalTicks", worldTotalTicks);
        
        WorldServer world = (WorldServer) this.world;
        File dataFile = new File(world.getChunkSaveLocation(), FoodStuffs.MOD_ID + "_world.dat");
        
        dataFile.mkdirs();
        
        try
        {
            CompressedStreamTools.write(worldData, dataFile);
        }
        catch(IOException e) {}
    }
    
    public void unload()
    {
        save();
    }
    
}
