package com.triangulum.foodstuffs.render.entity;

import com.triangulum.foodstuffs.entity.sheep.EntityModSheep;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderModSheep<T extends EntityModSheep> extends RenderLiving<T>
{
    
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("textures/entity/sheep/sheep.png");

    protected RenderModSheep(RenderManager renderManager)
    {
        super(renderManager, new ModelCow(), .7F);
        //this.addLayer(new LayerSheepWool(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SHEARED_SHEEP_TEXTURES;
    }

}
