package com.galaxyy.lifelocke.playerdata;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCopyHandler implements ServerPlayerEvents.CopyFrom {
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        ((iEntityDataSaver) newPlayer).lifelocke$getPersistentData().putIntArray("types",
                ((iEntityDataSaver) oldPlayer).lifelocke$getPersistentData().getIntArray("types").orElse(new int[] {}));
    }
}