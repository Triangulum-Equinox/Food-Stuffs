package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.pig.EntityModPig;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderModPig<T extends EntityModPig> extends RenderLiving<T>
{
    
    private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");
    
    protected RenderModPig(RenderManager renderManager)
    {
        super(renderManager, new ModelCow(), .7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityModPig entity)
    {
        return PIG_TEXTURES;
    }

}
