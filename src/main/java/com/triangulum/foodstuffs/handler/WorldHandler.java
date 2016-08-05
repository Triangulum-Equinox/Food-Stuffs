package com.triangulum.foodstuffs.handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.triangulum.foodstuffs.FoodStuffs;
import com.triangulum.foodstuffs.world.WorldData;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class WorldHandler
{
    public static Map<World, WorldData> worldData = new HashMap<World, WorldData>();

    public static WorldData getWorldData(World world)
    {
        return worldData.get(world);
    }

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event)
    {
        if(event.phase == Phase.START || event.world.isRemote || !worldData.containsKey(event.world))
            return;

        worldData.get(event.world).tick();
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event)
    {
        if(!event.getWorld().isRemote && event.getWorld().provider.getDimensionType() == DimensionType.OVERWORLD)
        {
            WorldServer world = (WorldServer) event.getWorld();

            File dimensionFile = new File(world.getChunkSaveLocation(), FoodStuffs.MOD_ID + "/world" + event.getWorld().provider.getDimension() + ".dat");
            if(dimensionFile.exists() && dimensionFile.isFile())
            {
                NBTTagCompound dimensionData;
                try
                {
                    dimensionData = CompressedStreamTools.read(dimensionFile);
                }
                catch(IOException e)
                {
                    return;
                }

                WorldData data = new WorldData(event.getWorld());

                data.load(dimensionData);

                worldData.put(event.getWorld(), data);
            }
        }
    }

    @SubscribeEvent
    public void worldSave(WorldEvent.Save event)
    {
        if(!event.getWorld().isRemote && event.getWorld().provider.getDimensionType() == DimensionType.OVERWORLD)
        {
            NBTTagCompound dimensionData = new NBTTagCompound();
            WorldData data = getWorldData(event.getWorld());
            
            data.save(dimensionData);
        }
    }

}
