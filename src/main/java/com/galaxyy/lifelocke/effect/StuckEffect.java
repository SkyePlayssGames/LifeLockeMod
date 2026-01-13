package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class StuckEffect extends MobEffect {
    protected StuckEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer)) {
            return;
        }
        UpdateData.setStuckBlockPos((ServerPlayer) entity, entity.blockPosition());
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer)) {
            return false;
        }
        BlockPos pos = UpdateData.getStuckBlockPos(((ServerPlayer) entity));
        if (pos == BlockPos.ZERO) {
            return false;
        }
        entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
        entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 5, 255, false, false));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
