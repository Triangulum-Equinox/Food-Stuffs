package com.triangulum.foodstuffs.world;

import java.util.List;

import codechicken.lib.world.WorldExtension;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ClientWorldExt extends WorldExtension
{
    
    public ClientWorldExt(World world)
    {
        super(world);
    }

    public ClientChunkExt getChunkExt(BlockPos pos)
    {
        Chunk chunk = world.getChunkFromBlockCoords(pos);
        
        if(chunk == null)
            return null;
        
        if(!chunkMap.containsKey(chunk))
            return null;
        
        return (ClientChunkExt) chunkMap.get(chunk);
    }
    
    public void addCrops(BlockPos pos, List<ClientCrop> cropList)
    {
        ClientChunkExt chunkExt = getChunkExt(pos);
        
        if(chunkExt == null)
            return;
        
        chunkExt.addCrops(cropList);
    }
    
    public void addCrop(ClientCrop crop)
    {
        ClientChunkExt chunkExt = getChunkExt(crop.getBlockPos());
        
        if(chunkExt == null)
            return;
        
        chunkExt.addCrop(crop);
    }

    public void removeCrop(BlockPos pos)
    {
        ClientChunkExt chunkExt = getChunkExt(pos);
        
        if(chunkExt == null)
            return;
        
        chunkExt.removeCrop(pos);
    }

    public ClientCrop getCrop(BlockPos pos)
    {
        ClientChunkExt chunkExt = getChunkExt(pos);
        
        if(chunkExt == null)
            return null;
        
        return chunkExt.getCrop(pos);
    }

}
