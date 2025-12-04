package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.util.UpdateData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Iterator;

public class PoisonEffect extends StatusEffect {
    protected PoisonEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        SettingsFileHandler.create();
        Boolean setting = SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()].get_boolean();
        if (UpdateData.togglePoisonPower((ServerPlayerEntity) entity) != setting) {
            UpdateData.togglePoisonPower(((ServerPlayerEntity) entity));
        }
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        for (StatusEffectInstance effectInstance : entity.getStatusEffects()) {
            if (effectInstance.getEffectType().value().getCategory() == StatusEffectCategory.HARMFUL) {
                entity.removeStatusEffect(effectInstance.getEffectType());
            }
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
