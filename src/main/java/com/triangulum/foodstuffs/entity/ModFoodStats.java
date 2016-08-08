package com.triangulum.foodstuffs.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;

public class ModFoodStats extends FoodStats
{
	
	private double hunger = 1.1;
	
	private int timer = 0;
	
    public void onUpdate(EntityPlayer player)
    {
    	if(!player.capabilities.disableDamage)
    	{
    		int difficulty = player.worldObj.getDifficulty().getDifficultyId() + 1;
    		
    		hunger -= (.000032) / (difficulty / 4d);
    		
    		if(hunger > .8 && difficulty <= 3)
    		{
    			if(timer++ == 80)
    			{
    				timer = 0;
    				player.heal(1);
    			}
    		}
    		else if(hunger <= .001)
    		{
    			player.attackEntityFrom(DamageSource.starve, 1000);
    		}
    		else
    		{
    			timer = 0;
    		}
    	}
    }
    
    public void addExhaustion(float exhaustion)
    {
    	hunger -= exhaustion / 1000d;
    }

    @Override
    public void addStats(int foodLevelIn, float foodSaturationModifier)
    {
        hunger += (foodLevelIn + Math.random() * 3 - 1) / 100d;
        hunger = Math.min(hunger, 1.1);
    }

    @Override
    public void addStats(ItemFood foodItem, ItemStack stack)
    {
        this.addStats(foodItem.getHealAmount(stack), foodItem.getSaturationModifier(stack));
    }
    
    @Override
    public void readNBT(NBTTagCompound playerNBT)
    {
    	if(playerNBT.hasKey("hunger"))
    	{
    		hunger = playerNBT.getDouble("hunger");
    	}
    }
    
    @Override
    public void writeNBT(NBTTagCompound playerNBT)
    {
    	playerNBT.setDouble("hunger", hunger);
    }
    
    @Override
    public boolean needFood()
    {
        return hunger < .9;
    }
    
    @Override
    public void setFoodLevel(int foodLevelIn)
    {
        hunger = foodLevelIn / 100d;
    }
    
    public double getHunger()
    {
        return hunger;
    }
}
