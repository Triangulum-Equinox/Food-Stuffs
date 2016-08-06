package com.triangulum.foodstuffs.crops;

import com.triangulum.foodstuffs.world.IWorldData;
import com.triangulum.foodstuffs.world.ServerWorldData;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ServerCrop extends Crop
{
    
    private int endWorldTick;
    
    private int growthStat;
    private int yieldStat;
    //might use this, could add multiple variables for different resistances, speculation for now though
    //private int resistanceStat;
    
    public ServerCrop(World worldIn, IWorldData data)
    {
        super(worldIn, data);
    }
    
    public ServerCrop(World worldIn, IWorldData worldData, BlockPos pos)
    {
        super(worldIn, worldData, pos);
    }

    public void nextGrowthStage()
    {
        IBlockState state = world.getBlockState(blockPos);
        
        world.markBlockRangeForRenderUpdate(blockPos, blockPos);
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
        super.loadFromStack(itemstack);
        
        loadPlantData(itemstack.getTagCompound());
    }
    
    public void load(NBTTagCompound plantData)
    {
        super.load(plantData);
        
        endWorldTick = plantData.getInteger("endWorldTick");
        
        loadPlantData(plantData);
    }
    
    public void save(NBTTagCompound plantData)
    {
        super.load(plantData);
        
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
