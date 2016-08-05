package com.triangulum.foodstuffs.plants;

import com.triangulum.foodstuffs.block.BlockCrop;
import com.triangulum.foodstuffs.world.WorldData;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Crop
{
    private World world;
    private WorldData worldData;
    private BlockPos blockPos;
    
    private int endWorldTick;
    
    private byte crop;
    
    private int growthStat;
    private int yieldStat;
    //might use this, could add multiple variables for different resistances, speculation for now though
    //private int resistanceStat;
    
    public Crop(World worldIn, WorldData data)
    {
        world = worldIn;
        worldData = data;
    }
    
    public Crop(World worldIn, WorldData data, BlockPos pos)
    {
        this(worldIn, data);
        blockPos = pos;
    }

    public void nextGrowthStage()
    {
        IBlockState state = world.getBlockState(blockPos);
        int age = state.getValue(BlockCrop.getAgeProperty()).intValue();
        
        world.setBlockState(blockPos, state.getBlock().getDefaultState().withProperty(BlockCrop.getAgeProperty(), age + 1));
    }
    
    public ItemStack getDroppedItemStack()
    {
        return null;
    }
    
    public void loadPlantData(NBTTagCompound plantData)
    {
        growthStat = plantData.getInteger("growthStat");
        yieldStat = plantData.getInteger("yieldStat");
        //resistanceStat = plantData.getInteger("resistanceStat");
    }
    
    public void loadFromStack(ItemStack itemstack)
    {
        crop = (byte) itemstack.getMetadata();
        
        loadPlantData(itemstack.getTagCompound());
    }
    
    public void load(NBTTagCompound plantData)
    {
        int xCoord = plantData.getInteger("xCoord");
        int yCoord = plantData.getInteger("yCoord");
        int zCoord = plantData.getInteger("zCoord");
        
        blockPos = new BlockPos(xCoord, yCoord, zCoord);
        
        endWorldTick = plantData.getInteger("endWorldTick");
        
        crop = plantData.getByte("crop");
        
        loadPlantData(plantData);
    }
    
    public void save(NBTTagCompound plantData)
    {
        plantData.setInteger("xCoord", blockPos.getX());
        plantData.setInteger("yCoord", blockPos.getY());
        plantData.setInteger("zCoord", blockPos.getZ());
        
        plantData.setByte("crop", crop);
        
        plantData.setInteger("endWorldTick", endWorldTick);
        
        plantData.setInteger("growthStat", growthStat);
        plantData.setInteger("yieldStat", yieldStat);
       //plantData.setInteger("resistanceStat", resistanceStat);
    }
    
    public BlockPos getBlockPos()
    {
        return blockPos;
    }
    
    public int getEndWorldTick()
    {
        return endWorldTick;
    }

}
