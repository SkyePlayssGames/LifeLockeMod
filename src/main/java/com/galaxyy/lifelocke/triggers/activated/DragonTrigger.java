package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3i;

public class DragonTrigger implements ActivatedAbility {
    public static final int DRAGON_BOOST_TIME = 400;

    @Override
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 20) || playerEntity.isCreative())) {
            return false;
        }

        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, DRAGON_BOOST_TIME, 1, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, DRAGON_BOOST_TIME, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, DRAGON_BOOST_TIME, 1, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, DRAGON_BOOST_TIME, 0, false, false));

        playerEntity.sendMessage(Text.translatable("text.lifelocke.dragon_activated"), false);

        playerEntity.getHungerManager().setFoodLevel(0);
        playerEntity.getHungerManager().setSaturationLevel(0);
        return true;
    }
}
