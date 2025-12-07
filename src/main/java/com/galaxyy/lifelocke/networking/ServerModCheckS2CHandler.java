package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerModCheckS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<ServerModCheckS2CPayload> {

    @Override
    public void receive(ServerModCheckS2CPayload payload, ClientPlayNetworking.Context context) {
        ((iEntityDataSaver) context.player()).lifelocke$getPersistentData().putBoolean("server_has_mod", true);
    }
}