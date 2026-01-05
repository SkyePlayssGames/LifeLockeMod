package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class RockTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return false;
        }
        playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.STUCK, 100, 0, false, false));
        HungerCost.takeHunger(playerEntity, 2);
        return true;
    }
}
