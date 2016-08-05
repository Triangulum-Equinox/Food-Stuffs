package com.triangulum.foodstuffs.handler.client;

import com.triangulum.foodstuffs.util.ModFoodStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUDHandler 
{

    @SubscribeEvent
    public void onRenderFood(RenderGameOverlayEvent.Pre event)
    {
        if(event.getType() != ElementType.FOOD)
            return;
        event.setCanceled(true);

        Minecraft mc = Minecraft.getMinecraft();
        int width = event.getResolution().getScaledWidth();
        int height = event.getResolution().getScaledHeight();
        
        mc.mcProfiler.startSection("food"); 

        GlStateManager.enableBlend();
        int left = width / 2 + 91;
        int top = height - 49;

        ModFoodStats stats = (ModFoodStats)mc.thePlayer.getFoodStats();
        double hunger = Math.min(stats.getHunger(), 1);
        
        for(int i = 0; i < 10; i++)
        {
            int x = left - i * 8 - 9;
            int y = top;
            int icon = 16;
            
            byte background = 0;

            if (mc.thePlayer.isPotionActive(MobEffects.HUNGER))
            {
                icon += 36;
                background = 13;
            }
            
            drawTexturedModalRect(x, y, 16 + background * 9, 27, 9, 9);
        }
        
        GlStateManager.disableBlend();
        mc.mcProfiler.endSection();
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(x + 0), (double)(y + height), (double)-90.0F).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(x + width), (double)(y + height), (double)-90.0F).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(x + width), (double)(y + 0), (double)-90.0F).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(x + 0), (double)(y + 0), (double)-90.0F).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
    
}
