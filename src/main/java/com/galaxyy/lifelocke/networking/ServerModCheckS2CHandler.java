package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.events.ModdedPlayerJoinClientCallback;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.jspecify.annotations.NonNull;

public class ServerModCheckS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<ServerModCheckS2CPayload> {

    @Override
    public void receive(ServerModCheckS2CPayload payload, ClientPlayNetworking.@NonNull Context context) {
        if (payload.serverVersion() == 0) {
            context.client().disconnect(Text.literal("The server kicked you!"));
            return;
        }

        ActionResult result = ModdedPlayerJoinClientCallback.EVENT.invoker().check(context.player(), payload.serverVersion());
        if (result == ActionResult.FAIL) {
            context.responseSender().sendPacket(new ClientDisconnectC2SPacket());
            context.client().disconnect(Text.literal("You can't connect to this server!"));
            return;
        }

        NbtCompound nbt = ((iEntityDataSaver) context.player()).lifelocke$getPersistentData();
        nbt.putBoolean("server_has_mod", true);
        nbt.putInt("server_version", payload.serverVersion());
        nbt.putInt("client_version", LifeLocke.CLIENT_VERSION);
    }
}