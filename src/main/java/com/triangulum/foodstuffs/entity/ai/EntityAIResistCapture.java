package com.triangulum.foodstuffs.entity.ai;

import com.triangulum.foodstuffs.entity.EntityModAnimal;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIResistCapture extends EntityAIBase
{
    
    private EntityModAnimal animal;
    
    public EntityAIResistCapture(EntityModAnimal entity)
    {
        animal = entity;
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
