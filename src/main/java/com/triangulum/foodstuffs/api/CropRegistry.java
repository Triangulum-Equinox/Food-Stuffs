package com.triangulum.foodstuffs.api;

import java.util.ArrayList;
import java.util.List;

public class CropRegistry
{

    private static List<ICrop> customCropList = new ArrayList<ICrop>();
    private static List<ICropRender> customCropRenderList = new ArrayList<ICropRender>();
    
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
        customCropRenderList.add(crop.getCropRender());
    }
    
}
