package com.triangulum.foodstuffs.init;

import com.triangulum.foodstuffs.FoodStuffs;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class Network
{
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(FoodStuffs.MOD_ID);

	public static void register()
	{
		
	}

}
