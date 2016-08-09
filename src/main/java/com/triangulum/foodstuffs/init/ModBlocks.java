package com.triangulum.foodstuffs.init;

import com.triangulum.foodstuffs.block.BlockModFarmland;
import com.triangulum.foodstuffs.block.BlockModGrass;
import com.triangulum.foodstuffs.block.BlockModTallGrass;
import com.triangulum.foodstuffs.entity.EntityModAnimal;
import com.triangulum.foodstuffs.util.Hacker;
import net.minecraft.block.Block;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{

    public static void register()
    {
        //the sketchiest form of hacking into minecraft in order to make tall grass naturally grow ... And I'm pretty sure I don't even want this form of growth either :P
        Hacker.hackRegisterBlock(new BlockModGrass(), "GRASS", new ResourceLocation("minecraft:grass"));
        Hacker.hackRegisterBlock(new BlockModTallGrass(), "TALLGRASS", new ResourceLocation("minecraft:tallgrass"));
        Hacker.hackRegisterBlock(new BlockModFarmland(), "FARMLAND", new ResourceLocation("minecraft:farmland"));
        /* try
        {
            GameRegistry.addSubstitutionAlias("grass", Type.BLOCK, new BlockModGrass());
            GameRegistry.addSubstitutionAlias("tallgrass", Type.BLOCK, new BlockModTallGrass());
        }
        catch (ExistingSubstitutionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        EntityModAnimal.spawnableBlock = Blocks.GRASS;
    }

    public static void registerTileEntities() 
    {

    }
    
    public static void registerClient()
    {
        
    }
    
    public static void comboRegister(Block block){
        GameRegistry.register(block);
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        GameRegistry.register(itemBlock);
    }
}
