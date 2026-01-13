package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;

public class CurseTypeTrigger implements ActivatedAbility {
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) {
            playerEntity.randomTeleport(pos.getX(), pos.getY(), pos.getZ(), true);
            HungerCost.takeHunger(playerEntity, 1);
            return true;
        } else {
            return false;
        }
    }
}
