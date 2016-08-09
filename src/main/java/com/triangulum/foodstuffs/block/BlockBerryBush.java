package com.triangulum.foodstuffs.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBerryBush extends Block { //need to make this growable

    public static List<ItemStack> drops;

    public BlockBerryBush(String unlocalizedName, ResourceLocation registryName, List<ItemStack> drops) {
        super(Material.PLANTS);
        this.drops = drops;
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(registryName);
        this.setCreativeTab(CreativeTabs.FOOD);
    }

    @Override
    public boolean isVisuallyOpaque() {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess p_isPassable_1_, BlockPos p_isPassable_2_) { //not sure what this is for, maybe for making intangable blocks?
        return true;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess p_isBlockSolid_1_, BlockPos p_isBlockSolid_2_, EnumFacing p_isBlockSolid_3_) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState ibs, IBlockAccess iba, BlockPos bp) {
        return false;
    }
    
    /*
    @Override
    public Item getItemDropped(IBlockState ibs, Random random, int i) { //for getting the block as an item? It may drop the defualt BlockItem.
        return somethingImportant;
    }
    */
    @Override
    public List<ItemStack> getDrops(IBlockAccess iba, BlockPos bp, IBlockState ibs, int i) {
        return drops;
    }

}
