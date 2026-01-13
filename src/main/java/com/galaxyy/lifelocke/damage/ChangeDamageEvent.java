package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.gamerule.ModGameRules;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import java.util.HashMap;

import static com.galaxyy.lifelocke.damage.ModDamageTypes.*;

public class ChangeDamageEvent implements ServerLivingEntityEvents.AllowDamage {
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (damageNotChanged(entity, source)) {
            return true;
        }
        assert entity instanceof Player;
        assert source.getEntity() instanceof Player;
        assert source.getEntity() != null;

        Player target = ((Player) entity);
        Player player = ((Player) source.getEntity());

        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            return changeDamageType(player, target, amount, PROJ_DAMAGE_TYPES);
        } else if (source.is(DamageTypeTags.IS_EXPLOSION)) {
            return true;
        } else if (source.is(DamageTypeTags.IS_FALL)) {
            return changeDamageType(player, target, amount, FALL_DAMAGE_TYPES);
        } else {
            return changeDamageType(player, target, amount, SLAIN_DAMAGE_TYPES);
        }
    }

    private static boolean changeDamageType(Player player, Player target, float amount,
                                            HashMap<Holder<MobEffect>, ResourceKey<DamageType>> typeMap) {
        for (MobEffectInstance effect : player.getActiveEffects()) {
            if (typeMap.containsKey(effect.getEffect())) {
                target.hurtServer(((ServerLevel) target.level()),
                        ModDamageTypes.of(target.level(), typeMap.get(effect.getEffect()), player),
                        amount);
                return false;
            }
        }
        return true;
    }

    private static boolean damageNotChanged(LivingEntity entity, DamageSource source) {
        if (!(source.getEntity() instanceof Player) || !(entity instanceof Player)) {
            return true;
        }
        if (!((ServerLevel) entity.level()).getGameRules().get(ModGameRules.TYPE_DEATH_MESSAGES)) {
            return true;
        }
        if (SLAIN_DAMAGE_TYPES.containsValue(source.typeHolder().unwrapKey().orElse(null)) ||
                PROJ_DAMAGE_TYPES.containsValue(source.typeHolder().unwrapKey().orElse(null)) ||
                FALL_DAMAGE_TYPES.containsValue(source.typeHolder().unwrapKey().orElse(null))) {
            return true;
        }
        return false;
    }
}
