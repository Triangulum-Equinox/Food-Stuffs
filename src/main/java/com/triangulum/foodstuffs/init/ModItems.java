package com.triangulum.foodstuffs.init;

import com.triangulum.foodstuffs.FoodStuffs;
import com.triangulum.foodstuffs.item.ItemCrop;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems 
{
	
	public static Item salt;
	public static Item crop;
	
	public static void register()
	{
		crop = new ItemCrop();
		
		GameRegistry.register(crop, new ResourceLocation(FoodStuffs.MOD_ID + ":crop"));
	}
	
    public static void registerClient()
    {
        
    }
}
