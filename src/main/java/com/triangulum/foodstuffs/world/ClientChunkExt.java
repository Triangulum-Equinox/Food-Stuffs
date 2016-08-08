package com.triangulum.foodstuffs.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codechicken.lib.world.ChunkExtension;
import codechicken.lib.world.WorldExtension;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class ClientChunkExt extends ChunkExtension
{

    private Map<BlockPos, ClientCrop> cropMap;

    public ClientChunkExt(Chunk chunk, WorldExtension world)
    {
        super(chunk, world);
        cropMap = new HashMap<BlockPos, ClientCrop>();
    }

    public void addCrops(List<ClientCrop> cropList)
    {
        for(ClientCrop crop : cropList)
        {
            addCrop(crop);
        }
    }

    public void addCrop(ClientCrop crop)
    {
        cropMap.put(crop.getBlockPos(), crop);
    }

    public void removeCrop(BlockPos pos)
    {
        if(!cropMap.containsKey(pos))
            return;

        cropMap.remove(pos);
    }

    public ClientCrop getCrop(BlockPos pos)
    {
        return cropMap.get(pos);
    }

}
