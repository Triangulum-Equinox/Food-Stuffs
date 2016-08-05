package com.triangulum.foodstuffs.entity;

import com.triangulum.foodstuffs.entity.ai.EntityAIFightOrFlight;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityModAnimal extends EntityCreature
{

    protected static final DataParameter<Float> HUNGER = EntityDataManager.<Float>createKey(EntityModAnimal.class, DataSerializers.FLOAT);

    public static final DataParameter<Boolean> GRAZE = EntityDataManager.<Boolean>createKey(EntityModAnimal.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> HAS_CHILD = EntityDataManager.<Boolean>createKey(EntityModAnimal.class, DataSerializers.BOOLEAN);
    
    public static Block spawnableBlock;
    
    public EntityModAnimal(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    protected void entityInit()
    {
        super.entityInit();
        
        dataManager.register(HUNGER, 1F);
        
        if(!isMale())
        {
            dataManager.register(HAS_CHILD, false);
        }
    }
    
    protected void initEntityAI()
    {
        tasks.addTask(0, new EntityAISwimming(this));
        
        targetTasks.addTask(0, new EntityAIFightOrFlight(this));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        
        registerAnimalStat(ModEntityAttributes.GROWTH);
        registerAnimalStat(ModEntityAttributes.AGGRESSION);
        registerAnimalStat(ModEntityAttributes.SIZE);
        registerAnimalStat(ModEntityAttributes.FERTILITY);
    }
    
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        float hunger = dataManager.get(HUNGER);
        if(hunger < .01)
        {
            attackEntityFrom(DamageSource.starve, 1000);
        }
        else
        {
            changeHunger(-.001f);
        }
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    protected void registerAnimalStat(IAttribute attribute)
    {
        getAttributeMap().registerAttribute(attribute).setBaseValue(.001 + worldObj.rand.nextDouble() * .005);;
    }
    
    protected void addAnimalStat(IAttribute attribute, double value)
    {
        IAttributeInstance instance = getEntityAttribute(attribute);
        instance.setBaseValue(value + instance.getBaseValue());
    }
    
    protected IAttributeInstance getHealthAttribute()
    {
        return getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
    }
    
    protected IAttributeInstance getSpeed()
    {
        return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
    }
    

    public void changeHunger(float foodAmount)
    {
        float currentHunger = dataManager.get(HUNGER);
        dataManager.set(HUNGER, Math.min(currentHunger + foodAmount, 1));
    }
    
    public void eat()
    {
        changeHunger(.1f);
    }
    
    public float getHunger()
    {
        return dataManager.get(HUNGER);
    }
    
    public double getGrowth()
    {
        return getEntityAttribute(ModEntityAttributes.GROWTH).getAttributeValue();
    }
    
    public double getAggression()
    {
        return getEntityAttribute(ModEntityAttributes.AGGRESSION).getAttributeValue();
    }
    
    public double getSize()
    {
        return getEntityAttribute(ModEntityAttributes.SIZE).getAttributeValue();
    }
    
    public double getFertility()
    {
        return getEntityAttribute(ModEntityAttributes.FERTILITY).getAttributeValue();
    }
    
    public boolean isChild()
    {
        return false;
    }
    
    public boolean isMale()
    {
        return false;
    }

}
