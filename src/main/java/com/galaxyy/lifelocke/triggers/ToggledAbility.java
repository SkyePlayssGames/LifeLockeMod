package com.galaxyy.lifelocke.triggers;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.function.Function;

public interface ToggledAbility {
    boolean toggle(ServerPlayerEntity playerEntity);
    default void updateData(ServerPlayerEntity playerEntity, Function<ServerPlayerEntity, Boolean> updateDataFunc,
                            RegistryEntry<StatusEffect> type) {
        if (updateDataFunc.apply(playerEntity)) {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.power_turned_on",
                    type.value().getName()), false);
        } else {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.power_turned_off",
                    type.value().getName()), false);
        }
    }
}
