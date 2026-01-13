package com.galaxyy.lifelocke.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class WaterEffect extends MobEffect {
    protected WaterEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 5, 0, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 5, 0, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 5, 0, false, false));

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
