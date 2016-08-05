package com.triangulum.foodstuffs.entity.sheep;

import net.minecraft.world.World;

public class EntityLamb extends EntityModSheep
{

    public EntityLamb(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public boolean isChild()
    {
        return true;
    }

}
