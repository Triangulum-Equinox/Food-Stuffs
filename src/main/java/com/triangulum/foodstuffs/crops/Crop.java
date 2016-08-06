package com.triangulum.foodstuffs.crops;

import com.triangulum.foodstuffs.world.IWorldData;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class Crop
{
    protected World world;
    protected IWorldData worldData;
    protected BlockPos blockPos;
    
    protected byte crop;
    protected byte cropStage;
    
    public Crop(World worldIn, IWorldData data)
    {
        world = worldIn;
        worldData = data;
    }
    
    public Crop(World worldIn, IWorldData worldData, BlockPos pos)
    {
        this(worldIn, worldData);
        blockPos = pos;
    }
    
    public void loadFromStack(ItemStack itemstack)
    {
        crop = (byte) itemstack.getMetadata();
        cropStage = (byte) 0;
    }
    
    public void load(NBTTagCompound plantData)
    {
        int xCoord = plantData.getInteger("xCoord");
        int yCoord = plantData.getInteger("yCoord");
        int zCoord = plantData.getInteger("zCoord");
        
        blockPos = new BlockPos(xCoord, yCoord, zCoord);
        
        crop = plantData.getByte("crop");
        cropStage = plantData.getByte("cropStage");
    }
    
    public void save(NBTTagCompound plantData)
    {
        plantData.setInteger("xCoord", blockPos.getX());
        plantData.setInteger("yCoord", blockPos.getY());
        plantData.setInteger("zCoord", blockPos.getZ());
        
        plantData.setByte("crop", crop);
        plantData.setByte("cropStage", cropStage);
    }
    
    public World getWorld()
    {
        return world;
    }
    
    public IWorldData getWorldData()
    {
        return worldData;
    }
    
    public BlockPos getBlockPos()
    {
        return blockPos;
    }

}
