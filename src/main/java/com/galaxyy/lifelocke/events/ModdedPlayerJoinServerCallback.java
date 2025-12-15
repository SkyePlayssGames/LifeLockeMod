package com.galaxyy.lifelocke.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

import java.util.UUID;

public interface ModdedPlayerJoinServerCallback {
    ActionResult check(ServerPlayerEntity player, int version);

    Event<ModdedPlayerJoinServerCallback> EVENT = EventFactory.createArrayBacked(ModdedPlayerJoinServerCallback.class,
            (listener) -> (player, version) -> {
                for (ModdedPlayerJoinServerCallback event : listener) {
                    ActionResult result = event.check(player, version);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });
}
