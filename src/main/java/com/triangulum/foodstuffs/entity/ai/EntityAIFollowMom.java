package com.triangulum.foodstuffs.entity.ai;

import com.triangulum.foodstuffs.entity.EntityModAnimal;
import com.triangulum.foodstuffs.entity.cattle.EntityModCow;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFollowMom extends EntityAIBase
{

    private EntityModAnimal animal;
    private EntityModAnimal mom;
    
    public EntityAIFollowMom(EntityModAnimal entity, EntityModCow parent)
    {
        animal = entity;
        mom = parent;
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
