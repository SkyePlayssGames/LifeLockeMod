package com.galaxyy.lifelocke.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public interface ModdedPlayerJoinClientCallback {
    InteractionResult check(Player player, int version);

    Event<ModdedPlayerJoinClientCallback> EVENT = EventFactory.createArrayBacked(ModdedPlayerJoinClientCallback.class,
            (listener) -> (player, version) -> {
                for (ModdedPlayerJoinClientCallback event : listener) {
                    InteractionResult result = event.check(player, version);
                    if (result != InteractionResult.PASS) {
                        return result;
                    }
                }
                return InteractionResult.PASS;
            });
}
