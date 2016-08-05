package com.triangulum.foodstuffs.entity.cattle;

import javax.annotation.Nullable;

import com.triangulum.foodstuffs.entity.ai.EntityAIFemaleAggression;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityModCow extends EntityCattle
{

    public EntityModCow(World worldIn)
    {
        super(worldIn);
        dataManager.set(HUNGER, .9f);
    }
    
    protected void initEntityAI()
    {
        super.initEntityAI();
        
        targetTasks.addTask(1, new EntityAIFemaleAggression(this, EntityPlayer.class));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getHealthAttribute().setBaseValue(10.0D);
        getSpeed().setBaseValue(0.20000000298023224D);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COW;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack)
    {
        if (stack != null && stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !isChild())
        {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);

            if (--stack.stackSize == 0)
            {
                player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
            {
                player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
            }

            return true;
        }
        else
        {
            if (stack != null && stack.getItem() == Items.SPAWN_EGG)
            {
                if (!worldObj.isRemote)
                {
                    Class <? extends Entity > oclass = EntityList.NAME_TO_CLASS.get(ItemMonsterPlacer.getEntityIdFromItem(stack));

                    if (oclass != null && getClass() == oclass)
                    {
                        /*EntityAgeable entityageable = createChild(this);

                        if (entityageable != null)
                        {
                            entityageable.setGrowingAge(-24000);
                            entityageable.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
                            worldObj.spawnEntityInWorld(entityageable);

                            if (stack.hasDisplayName())
                            {
                                entityageable.setCustomNameTag(stack.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode)
                            {
                                --stack.stackSize;
                            }
                        }*/
                    }
                }

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public float getEyeHeight()
    {
        return height;
    }

    public float getBlockPathWeight(BlockPos pos)
    {
        return worldObj.getBlockState(pos.down()).getBlock() == Blocks.GRASS ? 10.0F : worldObj.getLightBrightness(pos) - 0.5F;
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return 0.29D;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return worldObj.getBlockState(blockpos.down()).getBlock() == spawnableBlock && worldObj.getLight(blockpos) > 8 && super.getCanSpawnHere();
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 1 + worldObj.rand.nextInt(3);
    }

    /**
     * Decreases ItemStack size by one
     */
    protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 18)
        {
            for (int i = 0; i < 7; ++i)
            {
                double d0 = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle(EnumParticleTypes.HEART, posX + (double)(rand.nextFloat() * width * 2.0F) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), posZ + (double)(rand.nextFloat() * width * 2.0F) - (double)width, d0, d1, d2, new int[0]);
            }
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

}
