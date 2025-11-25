package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class WaterTrigger implements BlockUseConsumer {
    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return;
        }
        world.setBlockState(playerEntity.getBlockPos(), Blocks.WATER.getDefaultState());
        HungerCost.takeHunger(playerEntity, 1);
    }
}
