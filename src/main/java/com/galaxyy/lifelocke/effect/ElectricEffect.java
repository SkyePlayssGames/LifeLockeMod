package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ElectricEffect extends MobEffect {
    protected ElectricEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer)) {
            return true;
        }

        if (entity.isShiftKeyDown()) {
            UpdateData.incrementTimeSneaked(((ServerPlayer) entity));
        } else {
            UpdateData.resetTimeSneaked(((ServerPlayer) entity));
        }

        if (UpdateData.getTimeSneaked(((ServerPlayer) entity)) >= 20) {
            entity.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 2, false, false));
        }

        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        SettingsFileHandler.create();
        Boolean setting = SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()].get_boolean();
        if (UpdateData.toggleElectricPower((ServerPlayer) entity) != setting) {
            UpdateData.toggleElectricPower(((ServerPlayer) entity));
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
