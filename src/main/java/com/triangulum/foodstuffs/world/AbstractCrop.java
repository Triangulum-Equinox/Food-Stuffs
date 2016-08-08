package com.triangulum.foodstuffs.world;

import codechicken.lib.world.WorldExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractCrop
{
    
    protected World world;
    protected WorldExtension worldExt;
    protected BlockPos blockPos;
    
    protected byte crop;
    protected byte cropStage;
    
    public AbstractCrop(World worldIn, WorldExtension ext)
    {
        world = worldIn;
        worldExt = ext;
    }
    
    public AbstractCrop(World worldIn, WorldExtension ext, BlockPos pos)
    {
        this(worldIn, ext);
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
    
    public WorldExtension getWorldData()
    {
        return worldExt;
    }
    
    public BlockPos getBlockPos()
    {
        return blockPos;
    }
    
    public byte getCrop()
    {
        return crop;
    }
    
    public byte getCropStage()
    {
        return cropStage;
    }

}
