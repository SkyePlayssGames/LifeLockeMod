package com.galaxyy.lifelocke.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public interface ModdedPlayerJoinClientCallback {
    ActionResult check(PlayerEntity player, int version);

    Event<ModdedPlayerJoinClientCallback> EVENT = EventFactory.createArrayBacked(ModdedPlayerJoinClientCallback.class,
            (listener) -> (player, version) -> {
                for (ModdedPlayerJoinClientCallback event : listener) {
                    ActionResult result = event.check(player, version);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });
}
