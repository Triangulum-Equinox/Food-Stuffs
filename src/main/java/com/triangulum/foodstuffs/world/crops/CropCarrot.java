package com.triangulum.foodstuffs.world.crops;

import com.triangulum.foodstuffs.FoodStuffs;
import com.triangulum.foodstuffs.api.DefaultCropRender;
import com.triangulum.foodstuffs.api.ICrop;
import com.triangulum.foodstuffs.api.ICropRender;

import net.minecraft.util.ResourceLocation;

public class CropCarrot implements ICrop
{

    @Override
    public String getName()
    {
        return "Carrot";
    }

    public ICropRender getCropRender()
    {
        return new DefaultCropRender(new ResourceLocation(FoodStuffs.MOD_ID + ":crops/carrot"));
    }
    
}
