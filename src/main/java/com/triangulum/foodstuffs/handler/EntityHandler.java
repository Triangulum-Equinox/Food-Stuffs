package com.triangulum.foodstuffs.handler;

import java.lang.reflect.Field;

import com.triangulum.foodstuffs.entity.ModFoodStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityHandler
{

	@SubscribeEvent
	public void onEntityConstructed(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			try
			{
				EntityPlayer player = (EntityPlayer) event.getEntity();
				Field foodStatsField = EntityPlayer.class.getDeclaredField("foodStats");
				foodStatsField.setAccessible(true);
				foodStatsField.set(player, new ModFoodStats());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
