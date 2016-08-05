package com.triangulum.foodstuffs.entity.cattle;

import com.triangulum.foodstuffs.entity.ai.EntityAIFollowMom;

import net.minecraft.world.World;

public class EntityCalf extends EntityCattle
{

    private EntityModCow parent;
    
    public EntityCalf(World worldIn)
    {
        super(worldIn);
    }
    
    protected void initEntityAI()
    {
        super.initEntityAI();
        
        targetTasks.addTask(1, new EntityAIFollowMom(this, parent));
    }
    
    @Override
    public boolean isChild()
    {
        return true;
    }

}
