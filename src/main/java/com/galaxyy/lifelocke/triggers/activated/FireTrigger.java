package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class FireTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 6) || playerEntity.isCreative())
            || (playerEntity.isHolding(Items.SHIELD) && playerEntity.isUsingItem())) {
            return false;
        }
        EntityType.FIREBALL.spawn(playerEntity.getEntityWorld(), BlockPos.ofFloored(pos.getX(), pos.getY(), pos.getZ()).up(), SpawnReason.TRIGGERED)
                .setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0, 3, 0);
        HungerCost.takeHunger(playerEntity, 2);
        return true;
    }
}
