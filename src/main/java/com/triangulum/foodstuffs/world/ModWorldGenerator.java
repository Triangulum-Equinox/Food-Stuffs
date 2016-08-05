package com.triangulum.foodstuffs.world;

import java.util.Random;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGenerator implements IWorldGenerator
{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
    {
    	if(world.provider.getDimension() == 0)
    	{
    		int randX = chunkX * 16 + random.nextInt(16);
    		int randZ = chunkZ * 16 + random.nextInt(16);
    		
    		int y = world.func_189649_b(randX, randZ);
    		
            BlockPos pos = new BlockPos(randX, y, randZ);
            Biome biome = world.getBiome(pos);
            
            if(biome == Biomes.PLAINS)
            {
                world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState());
            }
    	}
    }
}