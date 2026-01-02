package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.UpdateData;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class NormalTrigger implements BlockUseConsumer {
    @Override
    public boolean accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        ItemStack heldItem = playerEntity.getMainHandStack();
        ItemStack storedItem = UpdateData.swapNormalItemStack(playerEntity, heldItem);
        playerEntity.setStackInHand(playerEntity.getActiveHand(), storedItem);

        return true;
    }
}
