package com.triangulum.foodstuffs.network;

import java.util.Map;

import com.triangulum.foodstuffs.FoodStuffs;
import com.triangulum.foodstuffs.world.AbstractCrop;
import com.triangulum.foodstuffs.world.Crop;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;
import codechicken.lib.world.WorldExtension;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class ServerPacketHandler implements IServerPacketHandler
{

    @Override
    public void handlePacket(PacketCustom packet, EntityPlayerMP sender, INetHandlerPlayServer handler)
    {
        switch(packet.getType())
        {

        }
    }
    
    public static void sendChunkWatchPacket(Map<BlockPos, Crop> cropMap, EntityPlayerMP player)
    {
        PacketCustom packet = new PacketCustom(getChannel(), Network.SERVER_CHUNK_WATCH);
        
        packet.writeInt(cropMap.size());
        
        for(BlockPos pos : cropMap.keySet())
        {
            Crop crop = cropMap.get(pos);
            
            packet.writeBlockPos(pos);
            packet.writeByte(crop.getCrop());
            packet.writeByte(crop.getCropStage());
            
            packet.sendToPlayer(player);
        }
    }

    public static void sendChunkUnWatchPacket(ChunkPos pos, EntityPlayerMP player)
    {
        PacketCustom packet = new PacketCustom(getChannel(), Network.SERVER_CHUNK_UNWATCH);
        
        packet.writeInt(pos.chunkXPos);
        packet.writeInt(pos.chunkZPos);
        
        //packet.sendToPlayer(player);
    }
    
    public static void sendCropWatchPacket(AbstractCrop crop)
    {
        PacketCustom packet = new PacketCustom(getChannel(), Network.SERVER_CROP_WATCH);
        BlockPos pos = crop.getBlockPos();
        
        packet.writeBlockPos(pos);
        packet.writeByte(crop.getCrop());
        packet.writeByte(crop.getCropStage());
        
        packet.sendToChunk(crop.getWorld(), pos.getX() >> 4, pos.getZ() >> 4);
    }
    
    public static void sendCropUnWatchPacket(WorldExtension world, BlockPos pos)
    {
        PacketCustom packet = new PacketCustom(getChannel(), Network.SERVER_CROP_UNWATCH);
        
        packet.writeBlockPos(pos);
        
        packet.sendToChunk(world.world, pos.getX() >> 4, pos.getZ() >> 4);
    }

    public static String getChannel()
    {
        return FoodStuffs.MOD_ID;
    }

}
