package com.galaxyy.lifelocke.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class DarkEffect extends ToggledMobEffect {
    protected DarkEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        long time = world.getDayTime() % 24000;
        if (time > 13000 && time < 23000) {
            entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 5, 0, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 5, 0, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 5, 0, false, false));
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
