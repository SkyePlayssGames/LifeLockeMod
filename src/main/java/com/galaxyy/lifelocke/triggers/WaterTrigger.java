package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class WaterTrigger implements BlockUseConsumer {
    @Override
    public boolean accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return false;
        }
        world.setBlockState(playerEntity.getBlockPos(), Blocks.WATER.getDefaultState());
        HungerCost.takeHunger(playerEntity, 1);
        return true;
    }
}
