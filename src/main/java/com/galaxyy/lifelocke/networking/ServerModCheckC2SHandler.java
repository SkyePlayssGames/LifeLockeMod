package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.events.ModdedPlayerJoinServerCallback;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

public class ServerModCheckC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<ServerModCheckC2SPayload> {

    @Override
    public void receive(ServerModCheckC2SPayload payload, ServerPlayNetworking.Context context) {
        InteractionResult result =  ModdedPlayerJoinServerCallback.EVENT.invoker().check(context.player(), payload.clientVersion());

        if (result == InteractionResult.FAIL) {
            context.responseSender().sendPacket(new ServerModCheckS2CPayload(0));
            context.server().getPlayerList().broadcastSystemMessage(
                    Component.translatable("text.lifelocke.compatibility.player_kicked_server", context.player().getName()),
                    false);
        } else {
            CompoundTag nbt = ((iEntityDataSaver) context.player()).lifelocke$getPersistentData();
            nbt.putBoolean("server_has_mod", true);
            nbt.putInt("client_version", payload.clientVersion());
            nbt.putInt("server_version", LifeLocke.SERVER_VERSION);
            context.responseSender().sendPacket(new ServerModCheckS2CPayload(LifeLocke.SERVER_VERSION));
        }
    }
}