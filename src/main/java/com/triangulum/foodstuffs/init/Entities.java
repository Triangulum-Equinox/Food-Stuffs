package com.triangulum.foodstuffs.init;

import com.triangulum.foodstuffs.FoodStuffs;
import com.triangulum.foodstuffs.entity.cattle.EntityBull;
import com.triangulum.foodstuffs.entity.cattle.EntityCalf;
import com.triangulum.foodstuffs.entity.cattle.EntityCattle;
import com.triangulum.foodstuffs.entity.cattle.EntityModCow;
import com.triangulum.foodstuffs.entity.chicken.EntityChick;
import com.triangulum.foodstuffs.entity.chicken.EntityHen;
import com.triangulum.foodstuffs.entity.chicken.EntityRooster;
import com.triangulum.foodstuffs.entity.pig.EntityBoar;
import com.triangulum.foodstuffs.entity.pig.EntityPiglet;
import com.triangulum.foodstuffs.entity.pig.EntitySow;
import com.triangulum.foodstuffs.entity.sheep.EntityDam;
import com.triangulum.foodstuffs.entity.sheep.EntityLamb;
import com.triangulum.foodstuffs.entity.sheep.EntityRam;
import com.triangulum.foodstuffs.render.entity.RenderCattleFactory;
import com.triangulum.foodstuffs.render.entity.RenderModChickenFactory;
import com.triangulum.foodstuffs.render.entity.RenderModPigFactory;
import com.triangulum.foodstuffs.render.entity.RenderModSheepFactory;
import com.triangulum.foodstuffs.util.Hacker;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Entities
{

    public static void register()
    {        
        EntityRegistry.registerModEntity(EntityModCow.class, "Cow", 0, FoodStuffs.instance, 100, 1, true, 4470310, 10592673);
        EntityRegistry.registerModEntity(EntityBull.class, "Bull", 1, FoodStuffs.instance, 100, 1, true, 4470310, 10592673);
        EntityRegistry.registerModEntity(EntityCalf.class, "Calf", 2, FoodStuffs.instance, 100, 1, true, 4470310, 10592673);
        
        EntityRegistry.registerModEntity(EntityHen.class, "Hen", 3, FoodStuffs.instance, 100, 1, true, 10592673, 16711680);
        EntityRegistry.registerModEntity(EntityRooster.class, "Rooster", 4, FoodStuffs.instance, 100, 1, true, 10592673, 16711680);
        EntityRegistry.registerModEntity(EntityChick.class, "Chick", 5, FoodStuffs.instance, 100, 1, true, 10592673, 16711680);
        
        EntityRegistry.registerModEntity(EntitySow.class, "Sow", 6, FoodStuffs.instance, 100, 1, true, 15771042, 14377823);
        EntityRegistry.registerModEntity(EntityBoar.class, "Boar", 7, FoodStuffs.instance, 100, 1, true, 15771042, 14377823);
        EntityRegistry.registerModEntity(EntityPiglet.class, "Piglet", 8, FoodStuffs.instance, 100, 1, true, 15771042, 14377823);
        
        EntityRegistry.registerModEntity(EntityDam.class, "Dam", 9, FoodStuffs.instance, 100, 1, true, 15198183, 16758197);
        EntityRegistry.registerModEntity(EntityRam.class, "Ram", 10, FoodStuffs.instance, 100, 1, true, 15198183, 16758197);
        EntityRegistry.registerModEntity(EntityLamb.class, "Lamb", 11, FoodStuffs.instance, 100, 1, true, 15198183, 16758197);
    }

    @SideOnly(Side.CLIENT)
    public static void registerClient()
    {        
        RenderingRegistry.registerEntityRenderingHandler(EntityCattle.class, new RenderCattleFactory<EntityCattle>());
        RenderingRegistry.registerEntityRenderingHandler(EntityBull.class, new RenderCattleFactory<EntityBull>());
        RenderingRegistry.registerEntityRenderingHandler(EntityCalf.class, new RenderCattleFactory<EntityCalf>());
        
        RenderingRegistry.registerEntityRenderingHandler(EntityHen.class, new RenderModChickenFactory<EntityHen>());
        RenderingRegistry.registerEntityRenderingHandler(EntityRooster.class, new RenderModChickenFactory<EntityRooster>());
        RenderingRegistry.registerEntityRenderingHandler(EntityChick.class, new RenderModChickenFactory<EntityChick>());
        
        RenderingRegistry.registerEntityRenderingHandler(EntitySow.class, new RenderModPigFactory<EntitySow>());
        RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderModPigFactory<EntityBoar>());
        RenderingRegistry.registerEntityRenderingHandler(EntityPiglet.class, new RenderModPigFactory<EntityPiglet>());
        
        RenderingRegistry.registerEntityRenderingHandler(EntityDam.class, new RenderModSheepFactory<EntityDam>());
        RenderingRegistry.registerEntityRenderingHandler(EntityRam.class, new RenderModSheepFactory<EntityRam>());
        RenderingRegistry.registerEntityRenderingHandler(EntityLamb.class, new RenderModSheepFactory<EntityLamb>());
    }
    
    public static void registerHacks()
    {
        Hacker.hackRegisterLiving(EntityCow.class, EntityCattle.class);
        Hacker.hackRegisterLiving(EntitySheep.class, EntityDam.class);
        Hacker.hackRegisterLiving(EntityPig.class, EntitySow.class);
        Hacker.hackRegisterLiving(EntityChicken.class, EntityHen.class);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerClientHacks()
    {
        Hacker.hackRenderLiving(EntityCow.class, EntityCattle.class);
        Hacker.hackRenderLiving(EntitySheep.class, EntityDam.class);
        Hacker.hackRenderLiving(EntityPig.class, EntitySow.class);
        Hacker.hackRenderLiving(EntityChicken.class, EntityHen.class);
    }

}
