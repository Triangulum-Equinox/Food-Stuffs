package com.triangulum.foodstuffs.entity.sheep;

import net.minecraft.world.World;

public class EntityRam extends EntityModSheep
{

    public EntityRam(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public boolean isMale()
    {
        return true;
    }

}
