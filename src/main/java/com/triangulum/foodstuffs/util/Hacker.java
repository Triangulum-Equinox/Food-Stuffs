package com.triangulum.foodstuffs.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockStateContainer.StateImplementation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Hacker 
{

    private static Field modifiersField;

    static
    {
        try
        {
            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static void hackRegisterBlock(Block blockToRegister, String blockName, ResourceLocation blockResource)
    {
        try
        {
            Method registerBlock = GameData.getBlockRegistry().getClass().getDeclaredMethod("addObjectRaw", Integer.TYPE, ResourceLocation.class, IForgeRegistryEntry.class);

            Method setDefaultState = Block.class.getDeclaredMethod("setDefaultState", IBlockState.class);

            Field blockField = Blocks.class.getField(blockName);
            Field blockDelegateField = Block.class.getField("delegate");

            Field blockStateContainerField = Block.class.getDeclaredField("blockState");
            Field blockStateBlockField = BlockStateContainer.class.getDeclaredField("block");
            Field blockStateField = StateImplementation.class.getDeclaredField("block");

            Field itemBlockField = ItemBlock.class.getField("block");

            Block blockToHack = (Block) blockField.get(null);

            registerBlock.setAccessible(true);

            setDefaultState.setAccessible(true);

            blockField.setAccessible(true);
            blockDelegateField.setAccessible(true);

            blockStateContainerField.setAccessible(true);
            blockStateBlockField.setAccessible(true);
            blockStateField.setAccessible(true);

            itemBlockField.setAccessible(true);

            removeFinalModifier(blockField);
            removeFinalModifier(blockDelegateField);

            removeFinalModifier(blockStateContainerField);
            removeFinalModifier(blockStateBlockField);
            removeFinalModifier(blockStateField);

            removeFinalModifier(itemBlockField);

            registerBlock.invoke(GameData.getBlockRegistry(), Block.getIdFromBlock(blockToHack), blockResource, blockToRegister);

            blockDelegateField.set(blockToRegister, blockToHack.delegate);

            BlockStateContainer container = ((BlockStateContainer) blockStateContainerField.get(blockToHack));

            for(IBlockState state : container.getValidStates())
            {
                blockStateField.set(state, blockToRegister);
            }

            blockStateBlockField.set(container, blockToRegister);

            blockStateContainerField.set(blockToRegister, container);
            setDefaultState.invoke(blockToRegister, blockToHack.getDefaultState());

            ItemBlock itemTallGrass = (ItemBlock) GameData.getBlockItemMap().get(blockToHack);

            GameData.getBlockItemMap().remove(blockToHack);
            GameData.getBlockItemMap().put(blockToRegister, itemTallGrass);

            itemBlockField.set(itemTallGrass, blockToRegister);

            blockField.set(null, blockToRegister);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void hackRegisterLiving(Class<? extends EntityLiving> defaultEntityClass, Class<? extends EntityLiving> entityClass)
    {
        try
        {
            Field classIdField = EntityList.class.getDeclaredField("CLASS_TO_ID");
            Field nameIdField = EntityList.class.getDeclaredField("NAME_TO_ID");

            Field entityPlacementField = EntitySpawnPlacementRegistry.class.getDeclaredField("ENTITY_PLACEMENTS");

            Field monsterListField = Biome.class.getDeclaredField("spawnableMonsterList");
            Field creatureListField = Biome.class.getDeclaredField("spawnableCreatureList");
            Field waterCreatureListField = Biome.class.getDeclaredField("spawnableWaterCreatureList");
            Field caveCreatureListField = Biome.class.getDeclaredField("spawnableCaveCreatureList");

            classIdField.setAccessible(true);
            nameIdField.setAccessible(true);

            entityPlacementField.setAccessible(true);

            monsterListField.setAccessible(true);
            creatureListField.setAccessible(true);
            waterCreatureListField.setAccessible(true);
            caveCreatureListField.setAccessible(true);

            Map<Class<? extends Entity>, Integer> classIdMap = (Map<Class<? extends Entity>, Integer>) classIdField.get(null);
            Map<String, Integer> nameIdMap = (Map<String, Integer>) nameIdField.get(null);
            Map<Class<?>, EntityLiving.SpawnPlacementType> placementMap = (Map<Class<?>, SpawnPlacementType>) entityPlacementField.get(null);

            int entityID = classIdMap.get(defaultEntityClass);
            String entityName = EntityList.CLASS_TO_NAME.get(defaultEntityClass);
            
            EntityList.CLASS_TO_NAME.remove(defaultEntityClass);
            EntityList.ID_TO_CLASS.remove(entityID);
            EntityList.NAME_TO_CLASS.remove(entityName);
            classIdMap.remove(defaultEntityClass);
            nameIdMap.remove(entityName);
            
            EntityList.ENTITY_EGGS.remove(entityName);

           // EntityList.CLASS_TO_NAME.put(entityClass, entityName);
           // EntityList.ID_TO_CLASS.put(entityID, entityClass);
           // EntityList.NAME_TO_CLASS.put(entityName, entityClass);
           // classIdMap.put(entityClass, entityID);
           // nameIdMap.put(entityName, entityID);

           // placementMap.put(entityClass, placementMap.get(defaultEntityClass));
            placementMap.remove(defaultEntityClass);

            List<Biome.SpawnListEntry> monsterList;
            List<Biome.SpawnListEntry> creatureList;
            List<Biome.SpawnListEntry> waterCreatureList;
            List<Biome.SpawnListEntry> caveCreatureList;
            
            for(ResourceLocation biomeResource : Biome.REGISTRY.getKeys())
            {
                Biome biome = Biome.REGISTRY.getObject(biomeResource);
                
                monsterList = (List<Biome.SpawnListEntry>) monsterListField.get(biome);
                creatureList = (List<Biome.SpawnListEntry>) creatureListField.get(biome);
                waterCreatureList = (List<Biome.SpawnListEntry>) waterCreatureListField.get(biome);
                caveCreatureList = (List<Biome.SpawnListEntry>) caveCreatureListField.get(biome);

                if(findEntity(monsterList, defaultEntityClass, entityClass));
                else if(findEntity(creatureList, defaultEntityClass, entityClass));
                else if(findEntity(waterCreatureList, defaultEntityClass, entityClass));
                else if(findEntity(caveCreatureList, defaultEntityClass, entityClass));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static boolean findEntity(List<Biome.SpawnListEntry> list, Class<? extends Entity> defaultClass, Class<? extends EntityLiving> injectClass)
    {
        for(Biome.SpawnListEntry entry : list)
        {
            if(entry.entityClass == defaultClass)
            {
                list.remove(entry);
                //entry.entityClass = injectClass;
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public static void hackRenderLiving(Class<? extends EntityLiving> defaultEntityClass, Class<? extends EntityLiving> entityClass, Render<? extends EntityLiving> renderer)
    {
        Map<Class<? extends Entity>, Render<? extends Entity>> renderMap = Minecraft.getMinecraft().getRenderManager().entityRenderMap;
        if(renderMap.containsKey(defaultEntityClass))
        {
            renderMap.remove(defaultEntityClass);
            //renderMap.put(entityClass, renderer);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void hackRenderLiving(Class<? extends EntityLiving> defaultEntityClass, Class<? extends EntityLiving> entityClass)
    {
        Map<Class<? extends Entity>, Render<? extends Entity>> renderMap = Minecraft.getMinecraft().getRenderManager().entityRenderMap;
        if(renderMap.containsKey(defaultEntityClass))
        {
            //renderMap.put(entityClass, renderMap.get(defaultEntityClass));
            renderMap.remove(defaultEntityClass);
        }
    }

    public static void removeFinalModifier(Field field) throws IllegalArgumentException, IllegalAccessException
    {
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }

}
