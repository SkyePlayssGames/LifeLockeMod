package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class StuckEffect extends StatusEffect {
    protected StuckEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayerEntity)) {
            return;
        }
        UpdateData.setStuckBlockPos((ServerPlayerEntity) entity, entity.getBlockPos());
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayerEntity)) {
            return false;
        }
        BlockPos pos = UpdateData.getStuckBlockPos(((ServerPlayerEntity) entity));
        if (pos == BlockPos.ORIGIN) {
            return false;
        }
        entity.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 5, 255, false, false));
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
