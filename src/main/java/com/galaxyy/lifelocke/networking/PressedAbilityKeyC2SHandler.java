package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.triggers.*;
import com.galaxyy.lifelocke.triggers.activated.*;
import com.galaxyy.lifelocke.triggers.toggled.*;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;

public class PressedAbilityKeyC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<@org.jetbrains.annotations.NotNull PressedAbilityKeyC2SPayload> {
    private static final HashMap<RegistryEntry<StatusEffect>, ToggledAbility> TOGGLED_ABILITIES = new HashMap<>();
    private static final HashMap<RegistryEntry<StatusEffect>, ActivatedAbility> ACTIVATED_ABILITIES = new HashMap<>();
    @Override
    public void receive(PressedAbilityKeyC2SPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity playerEntity = context.player();
        World world = context.player().getEntityWorld();

        for (StatusEffectInstance effect : playerEntity.getStatusEffects()) {
            if (TOGGLED_ABILITIES.containsKey(effect.getEffectType())) {
                if (TOGGLED_ABILITIES.get(effect.getEffectType()).toggle(playerEntity)) {
                    world.playSound(null, playerEntity.getBlockPos(), payload.toggledSoundEvent(), SoundCategory.PLAYERS);
                }
            } else if (ACTIVATED_ABILITIES.containsKey(effect.getEffectType())) {
                if (ACTIVATED_ABILITIES.get(effect.getEffectType()).activate(playerEntity, payload.hitPos())) {
                    world.playSound(null, playerEntity.getBlockPos(), payload.activatedSoundEvent(), SoundCategory.PLAYERS);
                }
            }
        }
    }

    public static void registerEffectMap() {
        TOGGLED_ABILITIES.clear();
        TOGGLED_ABILITIES.put(ModEffects.ELECTRIC, new ElectricTrigger());
        TOGGLED_ABILITIES.put(ModEffects.DARK, new DarkTrigger());
        TOGGLED_ABILITIES.put(ModEffects.POISON, new PoisonTrigger());
        TOGGLED_ABILITIES.put(ModEffects.PSYCHIC, new PsychicTrigger());
        TOGGLED_ABILITIES.put(ModEffects.ICE, new IceTrigger());
        TOGGLED_ABILITIES.put(ModEffects.GHOST, new GhostTrigger());

        ACTIVATED_ABILITIES.clear();
        ACTIVATED_ABILITIES.put(ModEffects.FIRE, new FireTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.CURSE_TYPE, new CurseTypeTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.DRAGON, new DragonTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.FAIRY, new FairyTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.FLYING, new FlyingTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.GRASS, new GrassTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.GROUND, new GroundTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.NORMAL, new NormalTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.ROCK, new RockTrigger());
        ACTIVATED_ABILITIES.put(ModEffects.WATER, new WaterTrigger());
    }
}
