package com.galaxyy.lifelocke.triggers;

import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;

public interface ActivatedAbility {
    boolean activate(ServerPlayer playerEntity, Vec3i pos);
}
