package com.triangulum.foodstuffs.config;

import com.triangulum.foodstuffs.FoodStuffs;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class FoodStuffsGuiConfig extends GuiConfig
{

    public FoodStuffsGuiConfig(GuiScreen parentScreen) 
    {
        super(parentScreen,
                new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                FoodStuffs.MOD_ID,
                true,
                true,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }
    //"true"s for needing to restart minecraft/server. Will resolve at completion.
}
