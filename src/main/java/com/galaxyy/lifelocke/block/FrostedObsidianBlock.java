package com.galaxyy.lifelocke.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FrostedIceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.block.WireOrientation;
import org.jspecify.annotations.Nullable;

public class FrostedObsidianBlock extends Block {
    public static final MapCodec<FrostedObsidianBlock> CODEC = createCodec(FrostedObsidianBlock::new);
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    private static final int NEIGHBORS_CHECKED_ON_SCHEDULED_TICK = 4;
    private static final int NEIGHBORS_CHECKED_ON_NEIGHBOR_UPDATE = 2;

    public FrostedObsidianBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, MathHelper.nextInt(world.getRandom(), 60, 120));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) == 0 || this.canMelt(world, pos, 4)) {
            int i = world.getRegistryKey() == World.END ? world.getLightLevel(LightType.BLOCK, pos) : world.getLightLevel(pos);
            if (i > 11 - (Integer)state.get(AGE) - state.getOpacity() && this.increaseAge(state, world, pos)) {
                BlockPos.Mutable mutable = new BlockPos.Mutable();

                for (Direction direction : Direction.values()) {
                    mutable.set(pos, direction);
                    BlockState blockState = world.getBlockState(mutable);
                    if (blockState.isOf(this) && !this.increaseAge(blockState, world, mutable)) {
                        world.scheduleBlockTick(mutable, this, MathHelper.nextInt(random, 20, 40));
                    }
                }

                return;
            }
        }

        world.scheduleBlockTick(pos, this, MathHelper.nextInt(random, 20, 40));
    }

    private boolean increaseAge(BlockState state, World world, BlockPos pos) {
        int i = (Integer)state.get(AGE);
        if (i < 3) {
            world.setBlockState(pos, state.with(AGE, i + 1), Block.NOTIFY_LISTENERS);
            return false;
        } else {
            this.melt(state, world, pos);
            return true;
        }
    }

    private BlockState getMeltedState() {
        return Blocks.LAVA.getDefaultState();
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.blocksMovement() || blockState.isLiquid()) {
            world.setBlockState(pos, getMeltedState());
        }
    }

    protected void melt(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, getMeltedState());
        world.updateNeighbor(pos, getMeltedState().getBlock(), null);
    }

    private boolean canMelt(BlockView world, BlockPos pos, int maxNeighbors) {
        int i = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (Direction direction : Direction.values()) {
            mutable.set(pos, direction);
            if (world.getBlockState(mutable).isOf(this)) {
                if (++i >= maxNeighbors) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return ItemStack.EMPTY;
    }
}
