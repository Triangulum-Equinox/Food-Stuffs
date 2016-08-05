package com.triangulum.foodstuffs.entity.ai;

import com.triangulum.foodstuffs.entity.EntityModAnimal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAIAggression extends EntityAITarget
{
    
    protected Class<? extends EntityLivingBase> targetEntity;
    
    public EntityAIAggression(EntityModAnimal entity, Class<? extends EntityLivingBase> target)
    {
        super(entity, true);
        targetEntity = target;
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
