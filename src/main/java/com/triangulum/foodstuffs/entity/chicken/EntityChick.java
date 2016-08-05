package com.triangulum.foodstuffs.entity.chicken;

import net.minecraft.world.World;

public class EntityChick extends EntityModChicken
{

    public EntityChick(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public boolean isChild()
    {
        return true;
    }

}
