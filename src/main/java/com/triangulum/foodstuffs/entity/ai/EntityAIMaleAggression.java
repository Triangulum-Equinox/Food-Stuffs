package com.triangulum.foodstuffs.entity.ai;

import com.triangulum.foodstuffs.entity.EntityModAnimal;

import net.minecraft.entity.EntityLivingBase;

public class EntityAIMaleAggression extends EntityAIAggression
{

    public EntityAIMaleAggression(EntityModAnimal entity, Class<? extends EntityLivingBase> target)
    {
        super(entity, target);
    }
    
    @Override
    public boolean shouldExecute()
    {
        return false;
    }
    
    @Override
    public void startExecuting()
    {
        
    }
    
    @Override
    public boolean continueExecuting()
    {
        return true;
    }

}
