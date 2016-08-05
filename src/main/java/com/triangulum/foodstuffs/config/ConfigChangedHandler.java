package com.triangulum.foodstuffs.config;

import static com.triangulum.foodstuffs.config.ConfigurationHandler.config;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigChangedHandler 
{

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) 
    {
        config.save();
    }
}
