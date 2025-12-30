package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.events.ModdedPlayerJoinServerCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public class CheckServerVersionEvent implements ModdedPlayerJoinServerCallback {
    private static final int MINIMUM_CLIENT_VERSION = 11;

    @Override
    public ActionResult check(ServerPlayerEntity player, int version) {
        if (MINIMUM_CLIENT_VERSION <= version) {
            return ActionResult.PASS;
        } else {
            return ActionResult.FAIL;
        }
    }
}
