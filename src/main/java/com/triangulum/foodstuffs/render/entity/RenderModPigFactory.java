package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.pig.EntityModPig;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderModPigFactory<T extends EntityModPig> implements IRenderFactory<T>
{
    
    @Override
    public Render<? super T> createRenderFor(RenderManager manager)
    {
        return new RenderModPig<T>(manager);
    }

}
