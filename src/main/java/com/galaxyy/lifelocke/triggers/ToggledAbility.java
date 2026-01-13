package com.galaxyy.lifelocke.triggers;

import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

public interface ToggledAbility {
    boolean toggle(ServerPlayer playerEntity);
    default void updateData(ServerPlayer playerEntity, Function<ServerPlayer, Boolean> updateDataFunc,
                            Holder<MobEffect> type) {
        if (updateDataFunc.apply(playerEntity)) {
            playerEntity.displayClientMessage(Component.translatable("text.lifelocke.power_turned_on",
                    type.value().getDisplayName()), false);
        } else {
            playerEntity.displayClientMessage(Component.translatable("text.lifelocke.power_turned_off",
                    type.value().getDisplayName()), false);
        }
    }
}
