package com.triangulum.foodstuffs.world;

import com.triangulum.foodstuffs.crops.Crop;

import net.minecraft.util.math.BlockPos;

public interface IWorldData
{

    public void tick();

    public void addCrop(Crop crop);

    public void removeCrop(BlockPos pos);
    
    public Crop getCrop(BlockPos pos);

}
