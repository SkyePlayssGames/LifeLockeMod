package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.*;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jackson.MapEntry;

import java.util.HashMap;
import java.util.Map;

public class PressedAbilityKeyC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<PressedAbilityKeyC2SPayload> {
    private static final HashMap<RegistryEntry<StatusEffect>, BlockUseConsumer> EFFECT_FUNCTION_MAP = new HashMap<>();

    @Override
    public void receive(PressedAbilityKeyC2SPayload payload, ServerPlayNetworking.Context context) {
        PlayerEntity playerEntity = context.player();
        World world = context.player().getEntityWorld();
        Hand hand = context.player().preferredHand;

        for (RegistryEntry<StatusEffect> effect: EFFECT_FUNCTION_MAP.keySet()) {
            if (playerEntity.hasStatusEffect(effect)) {
                EFFECT_FUNCTION_MAP.get(effect).accept(playerEntity, world, hand, payload.hitPos());
            }
        }
    }

    public static void registerEffectMap() {
        EFFECT_FUNCTION_MAP.put(ModEffects.ELECTRIC, new ElectricTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.FIRE, new FireTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.GRASS, new GrassTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.ICE, new IceTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.POISON, new PoisonTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.FLYING, new FlyingTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.GROUND, new GroundTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.FAIRY, new FairyTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.DRAGON, new DragonTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.DARK, new DarkTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.PSYCHIC, new PsychicTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.CURSE_TYPE, new CurseTypeTrigger());
        EFFECT_FUNCTION_MAP.put(ModEffects.WATER, new WaterTrigger());
    }
}
