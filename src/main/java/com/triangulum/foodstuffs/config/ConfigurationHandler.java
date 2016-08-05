package com.triangulum.foodstuffs.config;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationHandler {

    public static Configuration config;
    
    public static float tallGrassGrowthRate = .1f;

    public static void loadConfig(File configurationFile)
    {
    	config = new Configuration(configurationFile);
    	
        MinecraftForge.EVENT_BUS.register(new ConfigChangedHandler());
        
        try
        {
            config.load();
            Property tGGRProp = config.get(Configuration.CATEGORY_GENERAL, "Tall Grass Growth Rate", "100", "Determines how quickly Tall Grass will respawn. Values of 0 to 1000, lower is slower.");
            tallGrassGrowthRate = tGGRProp.getInt() / 1000f;
            if(tallGrassGrowthRate > 1){
                ConfigurationHandler.tallGrassGrowthRate = 1;
            } else if (tallGrassGrowthRate < 0){
                ConfigurationHandler.tallGrassGrowthRate = 0;
            }
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
