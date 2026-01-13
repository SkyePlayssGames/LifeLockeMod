package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

public class FireTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 6) || playerEntity.isCreative())
            || (playerEntity.isHolding(Items.SHIELD) && playerEntity.isUsingItem())) {
            return false;
        }
        EntityType.FIREBALL.spawn(playerEntity.level(), BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()).above(), EntitySpawnReason.TRIGGERED)
                .shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0, 3, 0);
        HungerCost.takeHunger(playerEntity, 2);
        return true;
    }
}
