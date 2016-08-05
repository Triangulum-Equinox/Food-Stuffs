package com.triangulum.foodstuffs.entity.ai;

import com.triangulum.foodstuffs.entity.EntityModAnimal;

import net.minecraft.entity.EntityLivingBase;

public class EntityAIFemaleAggression extends EntityAIAggression
{

    public EntityAIFemaleAggression(EntityModAnimal entity, Class<? extends EntityLivingBase> target)
    {
        super(entity, target);
    }
    
    @Override
    public boolean shouldExecute()
    {
        return !((EntityModAnimal) taskOwner).isMale() && taskOwner.getDataManager().get(EntityModAnimal.HAS_CHILD) && super.shouldExecute();
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
