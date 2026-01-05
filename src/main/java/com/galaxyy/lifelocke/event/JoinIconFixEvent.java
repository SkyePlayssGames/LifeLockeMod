package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.function.Function;

public class JoinIconFixEvent implements ServerPlayerEvents.Join {
    private static final HashMap<RegistryEntry<StatusEffect>, Function<ServerPlayerEntity, Boolean>> TOGGLE_TYPES = new HashMap<>();

    @Override
    public void onJoin(ServerPlayerEntity player) {
        for (RegistryEntry<StatusEffect> status : TOGGLE_TYPES.keySet()) {
            if (player.hasStatusEffect(status)) {
                TOGGLE_TYPES.get(status).apply(player);
                TOGGLE_TYPES.get(status).apply(player);
                return;
            }
        }
    }

    public static void registerJoinFixEventTypes() {
        TOGGLE_TYPES.put(ModEffects.DARK, UpdateData::toggleDarkPower);
        TOGGLE_TYPES.put(ModEffects.ICE, UpdateData::toggleIcePower);
        TOGGLE_TYPES.put(ModEffects.ELECTRIC, UpdateData::toggleElectricPower);
        TOGGLE_TYPES.put(ModEffects.PSYCHIC, UpdateData::togglePsychicPower);
        TOGGLE_TYPES.put(ModEffects.POISON, UpdateData::togglePoisonPower);
    }
}
