package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class CurseTypeTrigger implements BlockUseConsumer {
    public void accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) {
            playerEntity.teleport(pos.getX(), pos.getY(), pos.getZ(), true);
            HungerCost.takeHunger(playerEntity, 1);
        }
    }
}
