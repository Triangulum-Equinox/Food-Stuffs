package com.triangulum.foodstuffs;

import com.triangulum.foodstuffs.handler.EntityHandler;

import net.minecraftforge.common.MinecraftForge;

public class Common
{

	public void register()
	{
		MinecraftForge.EVENT_BUS.register(new EntityHandler());
	}

}
