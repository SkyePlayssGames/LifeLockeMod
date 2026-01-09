package com.galaxyy.lifelocke.playerdata;

import com.galaxyy.lifelocke.networking.CopyFromDeadPlayerS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCopyHandler implements ServerPlayerEvents.AfterRespawn {
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        NbtCompound old_nbt = ((iEntityDataSaver) oldPlayer).lifelocke$getPersistentData();
        NbtCompound new_nbt = ((iEntityDataSaver) newPlayer).lifelocke$getPersistentData();
        new_nbt.putIntArray("types", old_nbt.getIntArray("types").orElse(new int[] {}));
        new_nbt.putBoolean("server_has_mod", old_nbt.getBoolean("server_has_mod").orElse(false));
        ServerPlayNetworking.send(newPlayer, new CopyFromDeadPlayerS2CPayload(old_nbt));
    }
}