package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.events.ModdedPlayerJoinServerCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.ActionResult;

public class ServerModCheckC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<ServerModCheckC2SPayload> {

    @Override
    public void receive(ServerModCheckC2SPayload payload, ServerPlayNetworking.Context context) {
        ActionResult result =  ModdedPlayerJoinServerCallback.EVENT.invoker().check(context.player(), payload.clientVersion());

        if (result == ActionResult.FAIL) {
            context.responseSender().sendPacket(new ServerModCheckS2CPayload(0));
        } else {
            context.responseSender().sendPacket(new ServerModCheckS2CPayload(LifeLocke.SERVER_VERSION));
        }
    }
}