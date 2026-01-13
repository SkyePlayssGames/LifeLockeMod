package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class FairyTrigger implements ActivatedAbility {
    public static final int FAIRY_BOOST_TIME = 400;
    public static final int FAIRY_COOLDOWN = 1200;

    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())
            || playerEntity.hasEffect(MobEffects.GLOWING)) {
            return false;
        }

        playerEntity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, FAIRY_BOOST_TIME, 0, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, FAIRY_BOOST_TIME, 0, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, FAIRY_BOOST_TIME, 0, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, FAIRY_COOLDOWN, 0, false, false));

        playerEntity.displayClientMessage(Component.translatable("text.lifelocke.fairy_activated"), false);

        HungerCost.takeHunger(playerEntity, 1);
        return true;
    }
}
