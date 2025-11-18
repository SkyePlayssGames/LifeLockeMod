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

import java.util.Map;

public class PressedAbilityKeyC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<PressedAbilityKeyC2SPayload> {
    private static final Map<RegistryEntry<StatusEffect>, BlockUseConsumer> EFFECT_FUNCTION_MAP = Map.of(
            ModEffects.ELECTRIC, new ElectricTrigger(),
            ModEffects.FIRE, new FireTrigger(),
            ModEffects.GRASS, new GrassTrigger(),
            ModEffects.ICE, new IceTrigger(),
            ModEffects.POISON, new PoisonTrigger(),
            ModEffects.FLYING, new FlyingTrigger(),
            ModEffects.GROUND, new GroundTrigger(),
            ModEffects.FAIRY, new FairyTrigger(),
            ModEffects.DARK, new DarkTrigger(),
            ModEffects.CURSE_TYPE, new CurseTypeTrigger()
    );

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
}
