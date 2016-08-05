package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.chicken.EntityModChicken;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderModChickenFactory<T extends EntityModChicken> implements IRenderFactory<T>
{
    
    @Override
    public Render<? super T> createRenderFor(RenderManager manager)
    {
        return new RenderModChicken<T>(manager);
    }

}
