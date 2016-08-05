package com.triangulum.foodstuffs.entity.ai;

import com.triangulum.foodstuffs.entity.EntityModAnimal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;

public class EntityAIGraze extends EntityAIBase
{

    private final EntityModAnimal animal;
    
    private int xPosition;
    private int yPosition;
    private int zPosition;
    
    private Path navigationPath;

    public EntityAIGraze(EntityModAnimal entity)
    {
        animal = entity;
        xPosition = 0;
        yPosition = 0;
        zPosition = 0;
    }

    @Override
    public boolean shouldExecute()
    {
        if(!animal.getDataManager().get(EntityModAnimal.GRAZE) || animal.getRNG().nextFloat() < animal.getHunger() / 1.1f)
            return false;

        xPosition = (int) (animal.posX + animal.width / 2d);
        yPosition = (int) animal.posY;
        zPosition = (int) (animal.posZ + animal.width / 2d);

        if(isTallGrass(0, 0)) return true;

        boolean hasFoundFood = false;

        int distance = 1;

        int x, z;

        do
        {
            for(z = -distance; z <= distance; z ++)
            {
                if(isTallGrass(-distance, z) || isTallGrass(distance, z))
                {
                    hasFoundFood = true;
                    z = distance + 1;
                }
            }

            if(hasFoundFood) break;

            for(x = -distance + 1; x <= distance - 1; x ++)
            {
                if(isTallGrass(x, -distance) || isTallGrass(x, distance))
                {
                    hasFoundFood = true;
                    x = distance + 1;
                }
            }

            distance++;
        }
        while(distance != 5);

        if(!hasFoundFood)
        {
            animal.getDataManager().set(EntityModAnimal.GRAZE, false);
        }

        return hasFoundFood;
    }
    
    @Override
    public void startExecuting()
    {
        this.animal.getNavigator().setPath(navigationPath, 1);
    }

    @Override
    public boolean continueExecuting()
    {
        if(this.animal.getNavigator().getPath() != navigationPath)
            return false;
        
        if(navigationPath.getCurrentPos().squareDistanceTo(xPosition, yPosition, zPosition) < 2)
        {
            if(isTallGrass(0, 0))
            {
                this.animal.worldObj.setBlockState(new BlockPos(xPosition, yPosition, zPosition), Blocks.AIR.getDefaultState());
                animal.eat();
            }
            return false;
        }
        
        return true;
    }

    private boolean isTallGrass(int x, int z)
    {
        BlockPos blockPosition = new BlockPos(xPosition + x, yPosition, zPosition + z);
        IBlockState blockState = animal.worldObj.getBlockState(blockPosition);

        int y = 0;
        if(blockState != Blocks.TALLGRASS)
        {
            if(blockState == Blocks.AIR)
            {
                do
                {
                    y --;
                    blockPosition.down();

                    blockState = animal.worldObj.getBlockState(blockPosition);
                }
                while(y > -4 && blockState == Blocks.AIR);
            }
            else if(blockState == Blocks.GRASS)
            {
                y ++;
                blockPosition.up();

                blockState = animal.worldObj.getBlockState(blockPosition);
            }
            else
            {
                y += 2;
                blockPosition.up(2);

                blockState = animal.worldObj.getBlockState(blockPosition);

                if(blockState != Blocks.TALLGRASS)
                {
                    y ++;
                    blockPosition.up();

                    blockState = animal.worldObj.getBlockState(blockPosition);
                }
            }
            
            if(blockState != Blocks.TALLGRASS)
                return false;
        }
        
        Path path = animal.getNavigator().getPathToPos(blockPosition);
        
        if(path == null)
            return false;
        
        navigationPath = path;
        
        xPosition += x;
        yPosition += y;
        zPosition += z;
        
        return true;
    }

}
