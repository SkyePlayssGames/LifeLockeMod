package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class FireTrigger implements BlockUseConsumer {

    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
            || !(HungerCost.checkHunger(playerEntity, 6) || playerEntity.isCreative())) {
            return;
        }
        NbtCompound ExplosionNbt = new NbtCompound();
        ExplosionNbt.putByte("ExplosionPower", (byte) 5);
        EntityType.FIREBALL.spawn((ServerWorld) world, blockHitResult.getBlockPos().add(new Vec3i(0, 2, 0)), SpawnReason.TRIGGERED).readCustomDataFromNbt(ExplosionNbt);
        HungerCost.takeHunger(playerEntity, 3);
    }
}
