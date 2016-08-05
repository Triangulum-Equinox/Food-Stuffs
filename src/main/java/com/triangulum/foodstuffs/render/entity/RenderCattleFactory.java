package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.cattle.EntityCattle;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCattleFactory<T extends EntityCattle> implements IRenderFactory<T>
{

    @Override
    public Render<? super T> createRenderFor(RenderManager manager)
    {
        return new RenderCattle<T>(manager);
    }

}
