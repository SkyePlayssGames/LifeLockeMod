package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class RenderTypeIconS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<RenderTypeIconS2CPayload> {
    @Override
    public void receive(RenderTypeIconS2CPayload renderTypeIconS2CPayload, ClientPlayNetworking.Context context) {
        ((iEntityDataSaver) context.player()).lifelocke$getPersistentData().putString("toggled_power", renderTypeIconS2CPayload.id().toString());
    }
}
