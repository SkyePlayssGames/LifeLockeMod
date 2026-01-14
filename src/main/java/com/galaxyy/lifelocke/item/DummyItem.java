package com.galaxyy.lifelocke.item;

import java.util.Objects;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.block.TeraTrialBlockEntity;
import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class DummyItem extends Item {
    public DummyItem(Properties settings) {
        super(settings);
    }

    private static BlockPos subtract(BlockPos one, BlockPos other) {
        return new BlockPos(one.getX() - other.getX(), one.getY() - other.getY(), one.getZ() - other.getZ());
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (useOnContext.getLevel().isClientSide()) return InteractionResult.PASS;

        ServerLevel level = (ServerLevel) useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        ItemStack item = useOnContext.getItemInHand();

        if (level.getBlockState(blockPos).getBlock() == Blocks.TRIAL_SPAWNER) {
            item.set(ModDataComponents.DEBUG_BLOCKPOS, blockPos);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(blockPos).getBlock() == ModBlocks.TERA_TRIAL_BLOCK &&
                item.get(ModDataComponents.DEBUG_BLOCKPOS) != null) {
            ((TeraTrialBlockEntity) level.getBlockEntity(blockPos)).appendSpawnerOffset(
                    subtract(Objects.requireNonNull(item.get(ModDataComponents.DEBUG_BLOCKPOS)), blockPos)
            );
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
