package com.triangulum.foodstuffs.network;

import com.triangulum.foodstuffs.FoodStuffs;
import com.triangulum.foodstuffs.world.ClientCrop;
import com.triangulum.foodstuffs.world.ClientWorldExt;
import com.triangulum.foodstuffs.world.WorldExtManager;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IClientPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.math.BlockPos;

public class ClientPacketHandler implements IClientPacketHandler
{

    @Override
    public void handlePacket(PacketCustom packet, Minecraft mc, INetHandlerPlayClient handler)
    {
        ClientWorldExt clientWorldExt = WorldExtManager.clientWorldExt;
        switch(packet.getType())
        {
        case Network.SERVER_CHUNK_WATCH:
            handleChunkWatch(packet, mc, clientWorldExt, handler);
            break;
        case Network.SERVER_CHUNK_UNWATCH:
            //handleChunkUnWatch(packet, mc, clientWorldExt, handler);
            break;
        case Network.SERVER_CROP_WATCH:
            handleCropWatch(packet, mc, clientWorldExt, handler);
            break;
        case Network.SERVER_CROP_UNWATCH:
            handleCropUnWatch(packet, mc, clientWorldExt, handler);
            break;
        }
    }
    
    private static ClientCrop getCrop(PacketCustom packet)
    {
        BlockPos pos = packet.readBlockPos();
        byte crop = packet.readByte();
        byte cropStage = packet.readByte();
        
        return new ClientCrop(WorldExtManager.clientWorldExt, pos, crop, cropStage);
    }
    
    public static void handleChunkWatch(PacketCustom packet, Minecraft mc, ClientWorldExt worldExt, INetHandlerPlayClient handler)
    {
        for(int i = packet.readInt(); i > 0; i--)
        {
            worldExt.addCrop(getCrop(packet));
        }
    }
    
    public static void handleChunkUnWatch(PacketCustom packet, Minecraft mc, ClientWorldExt worldExt, INetHandlerPlayClient handler)
    {
        int x = packet.readInt();
        int z = packet.readInt();
        
        //worldExt.removeChunk(x, z);
    }
    
    public static void handleCropWatch(PacketCustom packet, Minecraft mc, ClientWorldExt worldExt, INetHandlerPlayClient handler)
    {
        worldExt.addCrop(getCrop(packet));
    }
    
    public static void handleCropUnWatch(PacketCustom packet, Minecraft mc, ClientWorldExt worldExt, INetHandlerPlayClient handler)
    {
        BlockPos pos = packet.readBlockPos();
        
        worldExt.removeCrop(pos);
    }
    
    public static String getChannel()
    {
        return FoodStuffs.MOD_ID;
    }

}
