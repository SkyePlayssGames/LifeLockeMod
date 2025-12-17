package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.events.ModdedPlayerJoinClientCallback;
import com.galaxyy.lifelocke.events.ModdedPlayerJoinServerCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

import java.util.ArrayList;

public class CheckClientVersionEvent implements ModdedPlayerJoinClientCallback {
    private static final int MAXIMUM_VERSION = 2;

    @Override
    public ActionResult check(ClientPlayerEntity player, int version) {
        if (MAXIMUM_VERSION >= version) {
            return ActionResult.PASS;
        } else {
            return ActionResult.FAIL;
        }
    }
}
