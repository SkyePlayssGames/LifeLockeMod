package com.galaxyy.lifelocke.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.Iterator;

public class PoisonEffect extends StatusEffect {
    protected PoisonEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
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
