package com.triangulum.foodstuffs.api;

import java.util.ArrayList;
import java.util.List;

public class CropRegistry
{

    public static List<ICrop> customCropList = new ArrayList<ICrop>();
    
    public static void registerCrop(ICrop crop)
    {
        for(ICrop cropIterator : customCropList)
        {
            if(cropIterator.getName().equalsIgnoreCase(crop.getName()))
            {
                return;
            }
        }
        customCropList.add(crop);
    }
    
}
