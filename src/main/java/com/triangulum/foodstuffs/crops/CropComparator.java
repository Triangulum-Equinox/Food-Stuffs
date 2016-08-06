package com.triangulum.foodstuffs.crops;

import java.util.Comparator;

public class CropComparator implements Comparator<ServerCrop>
{

    @Override
    public int compare(ServerCrop plant, ServerCrop otherPlant)
    {
        return plant.getEndWorldTick() - otherPlant.getEndWorldTick();
    }

}
