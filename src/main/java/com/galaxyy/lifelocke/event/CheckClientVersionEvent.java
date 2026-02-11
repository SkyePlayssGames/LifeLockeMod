package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.events.ModdedPlayerJoinClientCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public class CheckClientVersionEvent implements ModdedPlayerJoinClientCallback {
    private static final int MAXIMUM_SERVER_VERSION = 20;

    @Override
    public InteractionResult check(Player player, int version) {
        if (MAXIMUM_SERVER_VERSION >= version) {
            return InteractionResult.PASS;
        } else {
            return InteractionResult.FAIL;
        }
    }
}
