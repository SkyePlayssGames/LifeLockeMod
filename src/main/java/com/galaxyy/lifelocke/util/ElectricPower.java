package com.galaxyy.lifelocke.util;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ElectricPower implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (playerEntity.hasStatusEffect(ModEffects.ELECTRIC) && !world.isClient() &&
                (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) &&
                ((iEntityDataSaver) playerEntity).getPersistentData().getBoolean("electric_power")) {

            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, entity.getBlockPos(), SpawnReason.TRIGGERED);
            HungerCost.takeHunger(playerEntity, 2);
        }

        return ActionResult.PASS;
    }
}
