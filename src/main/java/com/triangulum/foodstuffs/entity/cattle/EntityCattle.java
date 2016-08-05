package com.triangulum.foodstuffs.entity.cattle;

import com.triangulum.foodstuffs.entity.EntityModAnimal;
import com.triangulum.foodstuffs.entity.ai.EntityAIGraze;
import com.triangulum.foodstuffs.entity.ai.EntityAIHerd;
import com.triangulum.foodstuffs.entity.ai.EntityAIMigrate;
import com.triangulum.foodstuffs.entity.ai.EntityAIModWander;
import com.triangulum.foodstuffs.entity.ai.EntityAIResistCapture;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityCattle extends EntityModAnimal
{

    public EntityCattle(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    protected void entityInit()
    {
        super.entityInit();
        
        this.dataManager.register(GRAZE, false);
    }
    
    protected void initEntityAI()
    {
        super.initEntityAI();
        
        tasks.addTask(1, new EntityAIHerd(this));
        tasks.addTask(2, new EntityAIResistCapture(this));
        tasks.addTask(3, new EntityAIGraze(this));
        tasks.addTask(4, new EntityAIMigrate(this));
        tasks.addTask(5, new EntityAIModWander(this));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if(getHunger() < .5f)
        {
            if(!this.dataManager.get(GRAZE))
                this.dataManager.set(GRAZE, true);
        }
        else if(getHunger() > .95f)
        {
            if(this.dataManager.get(GRAZE))
                this.dataManager.set(GRAZE, false);
        }
        
    }
    
}
