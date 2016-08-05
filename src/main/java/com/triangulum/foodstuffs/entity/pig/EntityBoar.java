package com.triangulum.foodstuffs.entity.pig;

import net.minecraft.world.World;

public class EntityBoar extends EntityModPig
{

    public EntityBoar(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public boolean isMale()
    {
        return true;
    }

}
