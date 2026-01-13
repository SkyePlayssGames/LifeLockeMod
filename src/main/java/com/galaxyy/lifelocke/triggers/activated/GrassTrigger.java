package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;

public class GrassTrigger implements ActivatedAbility {
    private final EntityType[] ANIMALS = {
            EntityType.COW, EntityType.SHEEP, EntityType.PIG, EntityType.CHICKEN
    };

    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return false;
        }

        ServerLevel world = playerEntity.level();
        InteractionHand hand = playerEntity.getUsedItemHand();

        ItemStack stack = new ItemStack(Items.BONE_MEAL);
        BlockHitResult blockHitResult = new BlockHitResult(
                playerEntity.position().add(0, 0, 0), Direction.UP,
                playerEntity.blockPosition(), false
        );
        stack.useOn(new UseOnContext(world, playerEntity, hand, stack, blockHitResult));
        blockHitResult = new BlockHitResult(
                playerEntity.position().add(0, 0, 0), Direction.UP,
                playerEntity.blockPosition().above(), false
        );
        stack.useOn(new UseOnContext(world, playerEntity, hand, stack, blockHitResult));
        blockHitResult = new BlockHitResult(
                playerEntity.position().add(0, 0, 0), Direction.UP,
                playerEntity.blockPosition().below(), false
        );
        stack.useOn(new UseOnContext(world, playerEntity, hand, stack, blockHitResult));
        HungerCost.takeHunger(playerEntity, 1);

        if (playerEntity.getRandom().nextInt(0, 10) == 0) {
            ANIMALS[playerEntity.getRandom().nextInt(0, ANIMALS.length)].spawn(((ServerLevel) world), playerEntity.blockPosition(), EntitySpawnReason.TRIGGERED);
            HungerCost.takeHunger(playerEntity, 2);
        }
        return true;
    }
}
