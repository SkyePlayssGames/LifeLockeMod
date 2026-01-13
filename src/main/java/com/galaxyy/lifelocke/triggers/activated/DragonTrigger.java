package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class DragonTrigger implements ActivatedAbility {
    public static final int DRAGON_BOOST_TIME = 400;

    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 20) || playerEntity.isCreative())) {
            return false;
        }

        playerEntity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, DRAGON_BOOST_TIME, 1, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, DRAGON_BOOST_TIME, 0, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 0, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, DRAGON_BOOST_TIME, 1, false, false));
        playerEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, DRAGON_BOOST_TIME, 0, false, false));

        playerEntity.displayClientMessage(Component.translatable("text.lifelocke.dragon_activated"), false);

        playerEntity.getFoodData().setFoodLevel(0);
        playerEntity.getFoodData().setSaturation(0);
        return true;
    }
}
