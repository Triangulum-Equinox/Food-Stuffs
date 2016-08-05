package com.triangulum.foodstuffs.block;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.SoundType;

public class BlockModFarmland extends BlockFarmland
{
    
    public BlockModFarmland()
    {
        this.setHardness(.6F);
        this.setSoundType(SoundType.GROUND);
        this.setUnlocalizedName("farmland");
    }

}
