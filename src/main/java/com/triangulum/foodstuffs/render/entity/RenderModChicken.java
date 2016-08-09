package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.chicken.EntityModChicken;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderModChicken<T extends EntityModChicken> extends RenderLiving<T>
{
    
    private static final ResourceLocation CHICKEN_TEXTURES = new ResourceLocation("textures/entity/chicken.png");

    protected RenderModChicken(RenderManager renderManager)
    {
        super(renderManager, new ModelCow(), .7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityModChicken entity)
    {
        return CHICKEN_TEXTURES;
    }
    
    protected float handleRotationFloat(EntityChicken livingBase, float partialTicks)
    {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

}
