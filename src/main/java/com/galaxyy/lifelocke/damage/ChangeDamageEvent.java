package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.gamerule.ModGameRules;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;

import static com.galaxyy.lifelocke.damage.ModDamageTypes.*;

public class ChangeDamageEvent implements ServerLivingEntityEvents.AllowDamage {
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (damageNotChanged(entity, source)) {
            return true;
        }
        assert entity instanceof PlayerEntity;
        assert source.getAttacker() instanceof PlayerEntity;
        assert source.getAttacker() != null;

        PlayerEntity target = ((PlayerEntity) entity);
        PlayerEntity player = ((PlayerEntity) source.getAttacker());

        if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
            return changeDamageType(player, target, amount, PROJ_DAMAGE_TYPES);
        } else if (source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            return true;
        } else if (source.isIn(DamageTypeTags.IS_FALL)) {
            return changeDamageType(player, target, amount, FALL_DAMAGE_TYPES);
        } else {
            return changeDamageType(player, target, amount, SLAIN_DAMAGE_TYPES);
        }
    }

    private static boolean changeDamageType(PlayerEntity player, PlayerEntity target, float amount,
                                            HashMap<RegistryEntry<StatusEffect>, RegistryKey<DamageType>> typeMap) {
        for (StatusEffectInstance effect : player.getStatusEffects()) {
            if (typeMap.containsKey(effect.getEffectType())) {
                target.damage(((ServerWorld) target.getEntityWorld()),
                        ModDamageTypes.of(target.getEntityWorld(), typeMap.get(effect.getEffectType()), player),
                        amount);
                return false;
            }
        }
        return true;
    }

    private static boolean damageNotChanged(LivingEntity entity, DamageSource source) {
        if (!(source.getAttacker() instanceof PlayerEntity) || !(entity instanceof PlayerEntity)) {
            return true;
        }
        if (!((ServerWorld) entity.getEntityWorld()).getGameRules().getValue(ModGameRules.TYPE_DEATH_MESSAGES)) {
            return true;
        }
        if (SLAIN_DAMAGE_TYPES.containsValue(source.getTypeRegistryEntry().getKey().orElse(null)) ||
                PROJ_DAMAGE_TYPES.containsValue(source.getTypeRegistryEntry().getKey().orElse(null)) ||
                FALL_DAMAGE_TYPES.containsValue(source.getTypeRegistryEntry().getKey().orElse(null))) {
            return true;
        }
        return false;
    }
}
