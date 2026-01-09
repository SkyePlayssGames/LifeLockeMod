package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

public class CopyFromDeadPlayerS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<CopyFromDeadPlayerS2CPayload> {
    @Override
    public void receive(CopyFromDeadPlayerS2CPayload copyFromDeadPlayerS2CPayload, ClientPlayNetworking.Context context) {
        NbtCompound old_nbt = copyFromDeadPlayerS2CPayload.old_player();
        NbtCompound new_nbt = ((iEntityDataSaver) MinecraftClient.getInstance().player).lifelocke$getPersistentData();

        new_nbt.putBoolean("server_has_mod", old_nbt.getBoolean("server_has_mod").orElse(false));
    }
}
