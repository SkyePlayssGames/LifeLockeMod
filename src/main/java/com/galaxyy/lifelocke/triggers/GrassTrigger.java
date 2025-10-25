package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

public class GrassTrigger implements BlockUseConsumer {
    private final EntityType[] ANIMALS = {
            EntityType.COW, EntityType.SHEEP, EntityType.PIG, EntityType.CHICKEN
    };

    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
                || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return;
        }

        ItemStack stack = new ItemStack(Items.BONE_MEAL);
        stack.useOnBlock(new ItemUsageContext(world, playerEntity, hand, stack, blockHitResult));
        HungerCost.takeHunger(playerEntity, 1);

        if (playerEntity.getRandom().nextBetweenExclusive(0, 10) == 0) {
            Arrays.stream(ANIMALS).findAny().get().spawn(((ServerWorld) world), playerEntity.getBlockPos(), SpawnReason.TRIGGERED);
            HungerCost.takeHunger(playerEntity, 2);
        }
    }
}
