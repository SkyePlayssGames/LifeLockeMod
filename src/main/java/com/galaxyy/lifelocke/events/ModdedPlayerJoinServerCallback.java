package com.galaxyy.lifelocke.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import java.util.UUID;

public interface ModdedPlayerJoinServerCallback {
    InteractionResult check(ServerPlayer player, int version);

    Event<ModdedPlayerJoinServerCallback> EVENT = EventFactory.createArrayBacked(ModdedPlayerJoinServerCallback.class,
            (listener) -> (player, version) -> {
                for (ModdedPlayerJoinServerCallback event : listener) {
                    InteractionResult result = event.check(player, version);
                    if (result != InteractionResult.PASS) {
                        return result;
                    }
                }
                return InteractionResult.PASS;
            });
}
