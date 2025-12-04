package com.galaxyy.lifelocke.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

@FunctionalInterface
public interface BlockUseConsumer {
    boolean accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos);
}
