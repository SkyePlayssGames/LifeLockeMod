package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.networking.ServerModCheckC2SPayload;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.jspecify.annotations.NonNull;

public class ModCheckExistingEvent implements ClientPlayConnectionEvents.Join{
    @Override
    public void onPlayReady(@NonNull ClientPacketListener handler, PacketSender sender, Minecraft client) {
        ((iEntityDataSaver) client.player).lifelocke$getPersistentData().putBoolean("server_has_mod", false);
        sender.sendPacket(new ServerModCheckC2SPayload(LifeLocke.CLIENT_VERSION));
    }
}
