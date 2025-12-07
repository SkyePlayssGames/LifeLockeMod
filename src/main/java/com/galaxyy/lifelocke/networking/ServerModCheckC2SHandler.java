package com.galaxyy.lifelocke.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerModCheckC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<ServerModCheckC2SPayload> {

    @Override
    public void receive(ServerModCheckC2SPayload payload, ServerPlayNetworking.Context context) {
        context.responseSender().sendPacket(new ServerModCheckS2CPayload());
    }
}