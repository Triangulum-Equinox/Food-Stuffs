package com.triangulum.foodstuffs.entity.cattle;

import com.triangulum.foodstuffs.entity.ai.EntityAIMaleAggression;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBull extends EntityCattle
{
    
    public EntityBull(World worldIn)
    {
        super(worldIn);
    }
    
    protected void initEntityAI()
    {
        super.initEntityAI();
        
        targetTasks.addTask(1, new EntityAIMaleAggression(this, EntityPlayer.class));
        targetTasks.addTask(1, new EntityAIMaleAggression(this, getClass()));
    }
    
    @Override
    public boolean isMale()
    {
        return true;
    }
    
}
