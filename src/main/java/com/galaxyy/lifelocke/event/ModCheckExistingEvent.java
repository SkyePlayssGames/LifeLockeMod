package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.networking.ServerModCheckC2SPayload;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ModCheckExistingEvent implements ClientPlayConnectionEvents.Join{
    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        ((iEntityDataSaver) client.player).lifelocke$getPersistentData().putBoolean("server_has_mod", false);
        sender.sendPacket(new ServerModCheckC2SPayload());
    }
}
