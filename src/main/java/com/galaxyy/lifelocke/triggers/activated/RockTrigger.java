package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class RockTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return false;
        }
        playerEntity.addEffect(new MobEffectInstance(ModEffects.STUCK, 100, 0, false, false));
        HungerCost.takeHunger(playerEntity, 2);
        return true;
    }
}
