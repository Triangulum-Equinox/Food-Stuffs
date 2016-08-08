package com.triangulum.foodstuffs.world;

import codechicken.lib.world.ChunkExtension;
import codechicken.lib.world.WorldExtension;
import codechicken.lib.world.WorldExtensionInstantiator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldExtManager extends WorldExtensionInstantiator
{
    
    @SideOnly(Side.CLIENT)
    public static ClientWorldExt clientWorldExt;

    @Override
    public WorldExtension createWorldExtension(World world)
    {
        if(world.isRemote)
        {
            clientWorldExt = new ClientWorldExt(world);
            return clientWorldExt;
        }
        return new WorldExt(world);
    }

    @Override
    public ChunkExtension createChunkExtension(Chunk chunk, WorldExtension world)
    {
        if(world instanceof ClientWorldExt)
            return new ClientChunkExt(chunk, world);
        return new ChunkExt(chunk, world);
    }

}
