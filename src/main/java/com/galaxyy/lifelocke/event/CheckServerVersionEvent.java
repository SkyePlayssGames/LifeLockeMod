package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.events.ModdedPlayerJoinServerCallback;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;

public class CheckServerVersionEvent implements ModdedPlayerJoinServerCallback {
    private static final int MINIMUM_CLIENT_VERSION = 16;

    @Override
    public InteractionResult check(ServerPlayer player, int version) {
        if (MINIMUM_CLIENT_VERSION <= version) {
            return InteractionResult.PASS;
        } else {
            return InteractionResult.FAIL;
        }
    }
}
