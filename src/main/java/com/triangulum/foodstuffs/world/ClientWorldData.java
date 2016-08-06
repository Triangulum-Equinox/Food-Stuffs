package com.triangulum.foodstuffs.world;

import java.util.Map;

import com.triangulum.foodstuffs.crops.Crop;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientWorldData implements IWorldData
{

    private Map<BlockPos, Crop> cropMap;

    @Override
    public void tick()
    {
        
    }

    @Override
    public void addCrop(Crop crop)
    {
        
    }

    @Override
    public void removeCrop(BlockPos pos)
    {
        
    }

    @Override
    public Crop getCrop(BlockPos pos)
    {
        return null;
    }
    
}
