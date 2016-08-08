package com.triangulum.foodstuffs.block;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;

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
