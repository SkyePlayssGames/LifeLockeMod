package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class GrassTrigger implements ActivatedAbility {
    private final EntityType[] ANIMALS = {
            EntityType.COW, EntityType.SHEEP, EntityType.PIG, EntityType.CHICKEN
    };

    @Override
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return false;
        }

        ServerWorld world = playerEntity.getEntityWorld();
        Hand hand = playerEntity.getActiveHand();

        ItemStack stack = new ItemStack(Items.BONE_MEAL);
        BlockHitResult blockHitResult = new BlockHitResult(
                playerEntity.getEntityPos().add(0, 0, 0), Direction.UP,
                playerEntity.getBlockPos(), false
        );
        stack.useOnBlock(new ItemUsageContext(world, playerEntity, hand, stack, blockHitResult));
        blockHitResult = new BlockHitResult(
                playerEntity.getEntityPos().add(0, 0, 0), Direction.UP,
                playerEntity.getBlockPos().up(), false
        );
        stack.useOnBlock(new ItemUsageContext(world, playerEntity, hand, stack, blockHitResult));
        blockHitResult = new BlockHitResult(
                playerEntity.getEntityPos().add(0, 0, 0), Direction.UP,
                playerEntity.getBlockPos().down(), false
        );
        stack.useOnBlock(new ItemUsageContext(world, playerEntity, hand, stack, blockHitResult));
        HungerCost.takeHunger(playerEntity, 1);

        if (playerEntity.getRandom().nextBetweenExclusive(0, 10) == 0) {
            ANIMALS[playerEntity.getRandom().nextBetweenExclusive(0, ANIMALS.length)].spawn(((ServerWorld) world), playerEntity.getBlockPos(), SpawnReason.TRIGGERED);
            HungerCost.takeHunger(playerEntity, 2);
        }
        return true;
    }
}
