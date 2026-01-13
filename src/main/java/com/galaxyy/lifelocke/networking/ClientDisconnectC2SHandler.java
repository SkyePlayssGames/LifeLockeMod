package com.galaxyy.lifelocke.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;

public class ClientDisconnectC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<ClientDisconnectC2SPacket> {
    @Override
    public void receive(ClientDisconnectC2SPacket payload, ServerPlayNetworking.Context context) {
        context.server().getPlayerList().broadcastSystemMessage(
                Component.translatable("text.lifelocke.compatibility.player_kicked_client", context.player().getName()),
                false);
    }
}
