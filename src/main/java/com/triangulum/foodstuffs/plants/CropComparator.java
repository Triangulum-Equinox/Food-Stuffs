package com.triangulum.foodstuffs.plants;

import java.util.Comparator;

public class CropComparator implements Comparator<Crop>
{

    @Override
    public int compare(Crop plant, Crop otherPlant)
    {
        return plant.getEndWorldTick() - otherPlant.getEndWorldTick();
    }

}
