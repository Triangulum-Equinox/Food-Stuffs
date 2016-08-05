package com.triangulum.foodstuffs.entity.chicken;

import net.minecraft.world.World;

public class EntityRooster extends EntityModChicken
{

    public EntityRooster(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public boolean isMale()
    {
        return true;
    }

}
