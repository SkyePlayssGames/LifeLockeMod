package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.*;
import com.galaxyy.lifelocke.triggers.activated.*;
import com.galaxyy.lifelocke.triggers.toggled.*;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import java.util.HashMap;

public class PressedAbilityKeyC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<@org.jetbrains.annotations.NotNull PressedAbilityKeyC2SPayload> {
    private static final HashMap<Holder<MobEffect>, ToggledAbility> TOGGLED_ABILITIES = new HashMap<>();
    private static final HashMap<Holder<MobEffect>, ActivatedAbility> ACTIVATED_ABILITIES = new HashMap<>();
    @Override
    public void receive(PressedAbilityKeyC2SPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayer playerEntity = context.player();
        Level world = context.player().level();

        for (MobEffectInstance effect : playerEntity.getActiveEffects()) {
            if (TOGGLED_ABILITIES.containsKey(effect.getEffect())) {
                if (TOGGLED_ABILITIES.get(effect.getEffect()).toggle(playerEntity)) {
                    world.playSound(null, playerEntity.blockPosition(), payload.toggledSoundEvent(), SoundSource.PLAYERS);
                }
            } else if (ACTIVATED_ABILITIES.containsKey(effect.getEffect())) {
                if (ACTIVATED_ABILITIES.get(effect.getEffect()).activate(playerEntity, payload.hitPos())) {
                    world.playSound(null, playerEntity.blockPosition(), payload.activatedSoundEvent(), SoundSource.PLAYERS);
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
