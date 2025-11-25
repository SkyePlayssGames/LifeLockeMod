package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class FireTrigger implements BlockUseConsumer {

    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
            || !(HungerCost.checkHunger(playerEntity, 6) || playerEntity.isCreative())
            || (playerEntity.isHolding(Items.SHIELD) && playerEntity.isUsingItem())) {
            return;
        }
        EntityType.FIREBALL.spawn((ServerWorld) world, BlockPos.ofFloored(pos.getX(), pos.getY(), pos.getZ()).up(), SpawnReason.TRIGGERED).setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0, 3, 0);
        HungerCost.takeHunger(playerEntity, 2);
    }
}
