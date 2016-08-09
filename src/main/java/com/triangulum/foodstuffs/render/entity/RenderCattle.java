package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.cattle.EntityCattle;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCattle<T extends EntityCattle> extends RenderLiving<T>
{
    
    private static final ResourceLocation COW_TEXTURES = new ResourceLocation("minecraft:textures/entity/cow/cow.png");

    protected RenderCattle(RenderManager renderManager)
    {
        super(renderManager, new ModelCow(), .7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCattle entity)
    {
        return COW_TEXTURES;
    }

}
