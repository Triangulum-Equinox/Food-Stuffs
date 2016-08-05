package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.sheep.EntityModSheep;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderModSheepFactory<T extends EntityModSheep> implements IRenderFactory<T>
{
    
    @Override
    public Render<? super T> createRenderFor(RenderManager manager)
    {
        return new RenderModSheep<T>(manager);
    }

}
