package com.triangulum.foodstuffs;

import com.triangulum.foodstuffs.handler.client.HUDHandler;
import com.triangulum.foodstuffs.init.ModBlocks;
import com.triangulum.foodstuffs.init.ModItems;
import com.triangulum.foodstuffs.network.ClientPacketHandler;
import com.triangulum.foodstuffs.world.ClientWorldExt;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.world.WorldExtensionManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class Client extends Common
{
	
	public void register()
	{
		super.register();
		
		MinecraftForge.EVENT_BUS.register(new HUDHandler());
		
		ModItems.registerClient();
		ModBlocks.registerClient();
		
		PacketCustom.assignHandler(FoodStuffs.MOD_ID, new ClientPacketHandler());
	}
	
	public static ClientWorldExt getClientWorldExtension(World world)
	{
	    if(!world.isRemote)
	        return null;
	    return (ClientWorldExt) WorldExtensionManager.getWorldExtension(world, worldExtManager.instantiatorID);
	}

}
