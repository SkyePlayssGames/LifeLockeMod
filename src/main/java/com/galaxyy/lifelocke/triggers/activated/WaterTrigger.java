package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3i;

public class WaterTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        playerEntity.getEntityWorld().setBlockState(playerEntity.getBlockPos(), Blocks.WATER.getDefaultState());
        HungerCost.takeHunger(playerEntity, 1);
        return true;
    }
}
