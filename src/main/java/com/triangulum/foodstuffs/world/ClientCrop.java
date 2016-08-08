package com.triangulum.foodstuffs.world;

import net.minecraft.util.math.BlockPos;

public class ClientCrop extends AbstractCrop
{
    
    public ClientCrop(ClientWorldExt worldExt, byte crop, byte cropStage)
    {
        super(worldExt.world, worldExt);
        this.crop = crop;
        this.cropStage = cropStage;
    }
    
    public ClientCrop(ClientWorldExt worldExt, BlockPos pos, byte crop, byte cropStage)
    {
        super(worldExt.world, worldExt, pos);
        this.crop = crop;
        this.cropStage = cropStage;
    }

}
