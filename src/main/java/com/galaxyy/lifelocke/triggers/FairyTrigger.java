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

public class FairyTrigger implements BlockUseConsumer {
    public static final int FAIRY_BOOST_TIME = 400;
    public static final int FAIRY_COOLDOWN = 1200;

    @Override
    public boolean accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
            || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())
            || playerEntity.hasStatusEffect(StatusEffects.GLOWING)) {
            return false;
        }

        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, FAIRY_BOOST_TIME, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, FAIRY_BOOST_TIME, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, FAIRY_BOOST_TIME, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, FAIRY_COOLDOWN, 0, false, false));

        playerEntity.sendMessage(Text.translatable("text.lifelocke.fairy_activated"), false);

        HungerCost.takeHunger(playerEntity, 1);
        return true;
    }
}
