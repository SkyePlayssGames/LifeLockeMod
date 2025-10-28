package com.galaxyy.lifelocke.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class DarkEffect extends StatusEffect {
    protected DarkEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        long time = entity.getWorld().getTimeOfDay();
        if (time > 13000 && time < 23000) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 5, 0, false, false));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 5, 0, false, false));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 0, false, false));
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
