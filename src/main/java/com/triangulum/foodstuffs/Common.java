package com.triangulum.foodstuffs;

import com.triangulum.foodstuffs.api.CropRegistry;
import com.triangulum.foodstuffs.handler.EntityHandler;
import com.triangulum.foodstuffs.network.ServerPacketHandler;
import com.triangulum.foodstuffs.world.WorldExt;
import com.triangulum.foodstuffs.world.WorldExtManager;
import com.triangulum.foodstuffs.world.crops.CropCarrot;
import com.triangulum.foodstuffs.world.crops.CropPotato;
import com.triangulum.foodstuffs.world.crops.CropWheat;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.world.WorldExtensionManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class Common
{
    
    protected static final WorldExtManager worldExtManager;
    
    static
    {
        worldExtManager = new WorldExtManager();
    }

	public void register()
	{
		MinecraftForge.EVENT_BUS.register(new EntityHandler());
		
		PacketCustom.assignHandler(FoodStuffs.MOD_ID, new ServerPacketHandler());
		
		WorldExtensionManager.registerWorldExtension(worldExtManager);
		
		CropRegistry.registerCrop(new CropCarrot());
		CropRegistry.registerCrop(new CropPotato());
		CropRegistry.registerCrop(new CropWheat());
	}
	
	public static WorldExt getWorldExtension(World world)
	{
	    if(world.isRemote)
	        return null;
	    return (WorldExt) WorldExtensionManager.getWorldExtension(world, worldExtManager.instantiatorID);
	}
	
}
