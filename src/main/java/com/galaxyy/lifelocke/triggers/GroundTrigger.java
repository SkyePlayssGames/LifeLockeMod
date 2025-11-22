package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class GroundTrigger implements BlockUseConsumer {
    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
                || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return;
        }

        if (playerEntity.isSneaking()) {
            world.setBlockState(playerEntity.getBlockPos().down(), Blocks.DIRT.getDefaultState());
            HungerCost.takeHunger(playerEntity, 0.25f);
        } else {
            BlockPos center = playerEntity.getBlockPos().down();
            BlockPos[] blocks = {
                    center.north().west(), center.north(), center.north().east(),
                    center.west(), center, center.east(),
                    center.south().west(), center.south(), center.south().east()
            };

            for (BlockPos blockPos : blocks) {
                world.setBlockState(blockPos, Blocks.STONE.getDefaultState());
            }

            HungerCost.takeHunger(playerEntity, 1);
        }
    }
}
