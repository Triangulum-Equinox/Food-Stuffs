package com.triangulum.foodstuffs.block;

import java.util.ArrayList;
import java.util.List;

import com.triangulum.foodstuffs.crops.Crop;
import com.triangulum.foodstuffs.crops.ServerCrop;
import com.triangulum.foodstuffs.handler.WorldHandler;
import com.triangulum.foodstuffs.world.IWorldData;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrop extends Block
{

    private static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    
    public BlockCrop()
    {
        super(Material.PLANTS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockCrop.getAgeProperty(), Integer.valueOf(0)));
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }
    
    public static PropertyInteger getAgeProperty()
    {
        return AGE;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if(worldIn.isRemote)
            return;

        IWorldData worldData = WorldHandler.getWorldData(worldIn);

        if(worldData == null)
            return;

        Crop crop = new ServerCrop(worldIn, worldData, pos);
        
        crop.loadFromStack(stack);
        worldData.addCrop(crop);

        int seedCount = stack.getTagCompound().getInteger("seedCount");

        if(seedCount == 1)
        {
            if(placer instanceof EntityPlayer)
            {
                ((EntityPlayer) placer).inventory.deleteStack(stack);
            }
        }
        else
        {
            stack.getTagCompound().setInteger("seedCount", seedCount - 1);
        }
    }
    
    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        if(worldIn.isRemote)
            return;
    }
    
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn)
    {
        if(worldIn.isRemote)
            return;
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if(worldIn.isRemote)
            return;
        
        IWorldData worldData = WorldHandler.getWorldData(worldIn);
        
        worldData.removeCrop(pos);
    }
    
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        return new ArrayList<ItemStack>();
    }

}
