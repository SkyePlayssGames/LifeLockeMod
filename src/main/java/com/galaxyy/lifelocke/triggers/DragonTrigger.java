package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class DragonTrigger implements BlockUseConsumer {
    public static final int DRAGON_BOOST_TIME = 400;

    @Override
    public boolean accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
            || !(HungerCost.checkHunger(playerEntity, 20) || playerEntity.isCreative())) {
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
