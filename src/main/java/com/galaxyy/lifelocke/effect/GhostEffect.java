package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GhostEffect extends ToggledMobEffect {
    protected GhostEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 5, 0, false, false));
        if (((iEntityDataSaver) entity).lifelocke$getPersistentData().getStringOr("toggled_power", "lifelocke:null").equals(this.id.toString())) {
            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 5, 0, false, false));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
