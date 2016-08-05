package com.triangulum.foodstuffs;

import com.triangulum.foodstuffs.handler.client.HUDHandler;
import com.triangulum.foodstuffs.init.ModBlocks;
import com.triangulum.foodstuffs.init.ModItems;

import net.minecraftforge.common.MinecraftForge;

public class Client extends Common
{
	
	public void register()
	{
		super.register();
		
		MinecraftForge.EVENT_BUS.register(new HUDHandler());
		
		ModItems.registerClient();
		ModBlocks.registerClient();
	}

}
