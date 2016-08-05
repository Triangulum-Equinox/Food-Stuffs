package com.triangulum.foodstuffs.entity;

import static com.triangulum.foodstuffs.FoodStuffs.MOD_ID;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class ModEntityAttributes
{
    
    public static final IAttribute GROWTH = new RangedAttribute(null, MOD_ID + ".growth", .001, .001, 1).setDescription("Growth");
    public static final IAttribute AGGRESSION = new RangedAttribute(null, MOD_ID + ".aggression", .001, .001, 1).setDescription("Default Aggression");
    public static final IAttribute SIZE = new RangedAttribute(null, MOD_ID + ".size", .001, .001, 1).setDescription("Animal Size");
    public static final IAttribute FERTILITY = new RangedAttribute(null, MOD_ID + ".fertility", .001, .001, 1).setDescription("Fertility");
    
    public static final IAttribute HORN_SIZE = new RangedAttribute(null, MOD_ID + ".horns", .001, .001, 1).setDescription("Horn Size");
    
    public static final IAttribute MILK_PRODUCTION = new RangedAttribute(null, MOD_ID + ".female.milk", .001, .001, 1).setDescription("Milk Production");

}
