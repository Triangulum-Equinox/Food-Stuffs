package com.triangulum.foodstuffs.crops;

import com.triangulum.foodstuffs.world.IWorldData;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClientCrop extends Crop
{
    
    public ClientCrop(World worldIn, IWorldData data)
    {
        super(worldIn, data);
    }
    
    public ClientCrop(World worldIn, IWorldData worldData, BlockPos pos)
    {
        super(worldIn, worldData, pos);
    }

}
