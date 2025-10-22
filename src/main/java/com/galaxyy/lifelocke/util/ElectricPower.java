package com.galaxyy.lifelocke.util;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffects;
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
        if (playerEntity.hasStatusEffect(StatusEffects.STRENGTH) && !world.isClient() && HungerCost.check_hunger(playerEntity, 6)) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, entity.getBlockPos(), SpawnReason.TRIGGERED);
            HungerCost.take_hunger(playerEntity, 2);
        }

        return ActionResult.PASS;
    }
}
