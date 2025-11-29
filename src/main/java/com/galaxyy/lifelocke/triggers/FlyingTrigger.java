package com.galaxyy.lifelocke.triggers;

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
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class FlyingTrigger implements BlockUseConsumer {

    @Override
    public void accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
            || !(HungerCost.checkHunger(playerEntity, 6) || playerEntity.isCreative())
            || (playerEntity.isHolding(Items.SHIELD) && playerEntity.isUsingItem())) {
            return;
        }

        EntityType.WIND_CHARGE.spawn((ServerWorld) world, playerEntity.getBlockPos().up(2), SpawnReason.TRIGGERED).setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0, 10, 0);
        HungerCost.takeHunger(playerEntity, 3);
    }
}
