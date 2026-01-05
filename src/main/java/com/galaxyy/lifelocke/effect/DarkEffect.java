package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class DarkEffect extends StatusEffect {
    protected DarkEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        SettingsFileHandler.create();
        Boolean setting = SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()].get_boolean();
        if (UpdateData.toggleDarkPower((ServerPlayerEntity) entity) != setting) {
            UpdateData.toggleDarkPower(((ServerPlayerEntity) entity));
        }
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        long time = world.getTimeOfDay() % 24000;
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
