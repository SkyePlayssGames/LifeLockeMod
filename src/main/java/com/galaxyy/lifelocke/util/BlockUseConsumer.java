package com.galaxyy.lifelocke.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

@FunctionalInterface
public interface BlockUseConsumer {
    void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos);
}
