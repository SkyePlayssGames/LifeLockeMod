package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;

public class ChangeDamageEvent implements ServerLivingEntityEvents.AllowDamage {
    private static final HashMap<RegistryEntry<StatusEffect>, RegistryKey<DamageType>> deathMessages = new HashMap<>();

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof PlayerEntity) ||
            deathMessages.containsValue(source.getTypeRegistryEntry().getKey().orElse(null))) {
            return true;
        }
        PlayerEntity player = (PlayerEntity) source.getAttacker();
        for (StatusEffectInstance effect : player.getStatusEffects()) {
            if (deathMessages.containsKey(effect.getEffectType())) {
                entity.damage(((ServerWorld) entity.getEntityWorld()),
                        ModDamageTypes.of(entity.getEntityWorld(), deathMessages.get(effect.getEffectType()), source.getAttacker()),
                        amount);
                return false;
            }
        }
        return true;
    }

    public static void registerDeathMessages() {
        deathMessages.put(ModEffects.BUG, ModDamageTypes.BUG);
        deathMessages.put(ModEffects.FIRE, ModDamageTypes.FIRE);
        deathMessages.put(ModEffects.DARK, ModDamageTypes.DARK);
        deathMessages.put(ModEffects.WATER, ModDamageTypes.WATER);
        deathMessages.put(ModEffects.GROUND, ModDamageTypes.GROUND);
        deathMessages.put(ModEffects.ROCK, ModDamageTypes.ROCK);
        deathMessages.put(ModEffects.ICE, ModDamageTypes.ICE);
        deathMessages.put(ModEffects.PSYCHIC, ModDamageTypes.PSYCHIC);
        deathMessages.put(ModEffects.FLYING, ModDamageTypes.FLYING);
        deathMessages.put(ModEffects.POISON, ModDamageTypes.POISON);
        deathMessages.put(ModEffects.GRASS, ModDamageTypes.GRASS);
        deathMessages.put(ModEffects.ELECTRIC, ModDamageTypes.ELECTRIC);
        deathMessages.put(ModEffects.FIGHTING, ModDamageTypes.FIGHTING);
        deathMessages.put(ModEffects.GHOST, ModDamageTypes.GHOST);
        deathMessages.put(ModEffects.STEEL, ModDamageTypes.STEEL);
        deathMessages.put(ModEffects.CURSE_TYPE, ModDamageTypes.CURSE_TYPE);
        deathMessages.put(ModEffects.DRAGON, ModDamageTypes.DRAGON);
        deathMessages.put(ModEffects.FAIRY, ModDamageTypes.FAIRY);
    }
}
