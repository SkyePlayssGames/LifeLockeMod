package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3i;

public class CurseTypeTrigger implements ActivatedAbility {
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        if (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) {
            playerEntity.teleport(pos.getX(), pos.getY(), pos.getZ(), true);
            HungerCost.takeHunger(playerEntity, 1);
            return true;
        } else {
            return false;
        }
    }
}
