package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ToggledMobEffect extends MobEffect {
    protected Identifier id;

    protected ToggledMobEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    @Override
    public void onEffectStarted(LivingEntity livingEntity, int i) {
        SettingsFileHandler.create();
        BooleanSetting defaultTrue = (BooleanSetting) SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()];
        System.out.println("Type: " + Types.getType(id));
        UpdateData.setToggledPower(((ServerPlayer) livingEntity), Types.getType(id), false);
        boolean powerOn = ((iEntityDataSaver) livingEntity).lifelocke$getPersistentData().getStringOr("toggled_power", "lifelocke:null").equals(id.toString());
        if (defaultTrue.get_boolean() != powerOn) {
            UpdateData.setToggledPower(((ServerPlayer) livingEntity), Types.getType(id), false);
        }
    }
}
