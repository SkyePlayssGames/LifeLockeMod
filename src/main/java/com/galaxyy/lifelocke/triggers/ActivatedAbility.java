package com.galaxyy.lifelocke.triggers;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3i;

public interface ActivatedAbility {
    boolean activate(ServerPlayerEntity playerEntity, Vec3i pos);
}
