package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;

public class WaterTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        playerEntity.level().setBlockAndUpdate(playerEntity.blockPosition(), Blocks.WATER.defaultBlockState());
        HungerCost.takeHunger(playerEntity, 1);
        return true;
    }
}
