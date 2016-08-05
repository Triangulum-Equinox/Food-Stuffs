package com.triangulum.foodstuffs.entity.pig;

import net.minecraft.world.World;

public class EntityPiglet extends EntityModPig
{

    public EntityPiglet(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public boolean isChild()
    {
        return true;
    }

}
