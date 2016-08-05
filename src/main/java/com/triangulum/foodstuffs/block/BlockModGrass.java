package com.triangulum.foodstuffs.block;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

//Needs to be rebalanced of course!
public class BlockModGrass extends BlockGrass
{
	
	public BlockModGrass()
	{
		super();
		this.setHardness(0.6F);
		this.setSoundType(SoundType.PLANT);
		this.setUnlocalizedName("grass");
	}
	
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                        {
                            continue;
                        }

                        IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                        if (iblockstate1.getBlock() == Blocks.DIRT && iblockstate1.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2)
                        {
                            worldIn.setBlockState(blockpos, Blocks.GRASS.getDefaultState());
                        }
                    }
                    if(worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState())
                    {
                    	//Biome biomeGrassIsIn = worldIn.getBiomeForCoordsGen(pos);
                    	//I'd like to implement something that makes grass harder to grow in certain biomes, but for now ...
                    	//also need to allow for more variety than just tall grass
                    	if(rand.nextInt(4) >= worldIn.getDifficulty().getDifficultyId())
                    	{
                    		worldIn.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(BlockTallGrass.EnumType.GRASS.getMeta()));
                    	}
                    }
                }
            }
        }
    }

}
