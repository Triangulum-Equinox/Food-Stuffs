package com.triangulum.foodstuffs;

import com.triangulum.foodstuffs.config.ConfigurationHandler;
import com.triangulum.foodstuffs.handler.client.GuiHandler;
import com.triangulum.foodstuffs.init.Entities;
import com.triangulum.foodstuffs.init.ModBlocks;
import com.triangulum.foodstuffs.init.ModItems;
import com.triangulum.foodstuffs.init.Recipes;
import com.triangulum.foodstuffs.world.ModWorldGenerator;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

//annotation for mod initialization, if we are keeping this mod as a regular mod then we'll continue using this ...
@Mod(modid = FoodStuffs.MOD_ID, name = "Food Stuffs", version = "TEST", guiFactory = "com.triangulum.foodstuffs.config.GuiFactory")
public class FoodStuffs 
{

	//ID for our mod
    public static final String MOD_ID = "foodstuffs";

    //instance variable
    @Mod.Instance(MOD_ID)
    public static FoodStuffs instance;

    //proxy variable
    @SidedProxy(clientSide = "com.triangulum.foodstuffs.Client", serverSide = "com.triangulum.foodstuffs.Common")
    public static Common proxy;

    @Mod.EventHandler
    public void preInitialization(FMLPreInitializationEvent event) 
    {
    	//load our config
        ConfigurationHandler.loadConfig(event.getSuggestedConfigurationFile());

        //register any items we need
        ModItems.register();

        //register any blocks we need
        ModBlocks.register();
        
        //register all block tile entitites
        ModBlocks.registerTileEntities();
        
        //register entities we use
        Entities.register();
        if(proxy instanceof Client)
        {
            Entities.registerClient();
        }
    }

    @Mod.EventHandler
    public void initialization(FMLInitializationEvent event)
    {
    	//create handler for all our guis
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        //delete all vanilla farm animals
        Entities.registerHacks();
        if(proxy instanceof Client)
        {
            Entities.registerClientHacks();
        }
        
        //register event handlers
        proxy.register();
        
        //register recipes
        Recipes.register();
        
        //register our world generator for cool stuff to be added into the world.
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 0);
    }

    @Mod.EventHandler
    public void postInitialization(FMLInitializationEvent event)
    {
    }

}
