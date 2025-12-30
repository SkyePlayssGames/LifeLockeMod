package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.events.ModdedPlayerJoinClientCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public class CheckClientVersionEvent implements ModdedPlayerJoinClientCallback {
    private static final int MAXIMUM_SERVER_VERSION = 11;

    @Override
    public ActionResult check(PlayerEntity player, int version) {
        if (MAXIMUM_SERVER_VERSION >= version) {
            return ActionResult.PASS;
        } else {
            return ActionResult.FAIL;
        }
    }
}
