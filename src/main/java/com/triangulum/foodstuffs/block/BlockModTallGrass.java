package com.triangulum.foodstuffs.block;

import java.util.Random;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockModTallGrass extends BlockTallGrass
{
	
	public BlockModTallGrass()
	{
		super();
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
		this.setUnlocalizedName("tallgrass");
	}

}
