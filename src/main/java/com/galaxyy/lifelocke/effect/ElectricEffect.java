package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.util.UpdateData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class ElectricEffect extends StatusEffect {
    protected ElectricEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayerEntity)) {
            return true;
        }

        if (entity.isSneaking()) {
            UpdateData.incrementTimeSneaked(((ServerPlayerEntity) entity));
        } else {
            UpdateData.resetTimeSneaked(((ServerPlayerEntity) entity));
        }

        if (UpdateData.getTimeSneaked(((ServerPlayerEntity) entity)) >= 20) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 2, false, false));
        }

        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        SettingsFileHandler.create();
        Boolean setting = SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()].get_boolean();
        if (UpdateData.toggleElectricPower((ServerPlayerEntity) entity) != setting) {
            UpdateData.toggleElectricPower(((ServerPlayerEntity) entity));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
