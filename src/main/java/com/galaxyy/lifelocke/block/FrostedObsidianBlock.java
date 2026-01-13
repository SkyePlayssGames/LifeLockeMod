package com.galaxyy.lifelocke.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jspecify.annotations.Nullable;

public class FrostedObsidianBlock extends Block {
    public static final MapCodec<FrostedObsidianBlock> CODEC = simpleCodec(FrostedObsidianBlock::new);
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final int NEIGHBORS_CHECKED_ON_SCHEDULED_TICK = 4;
    private static final int NEIGHBORS_CHECKED_ON_NEIGHBOR_UPDATE = 2;

    public FrostedObsidianBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleTick(pos, this, Mth.nextInt(world.getRandom(), 60, 120));
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (random.nextInt(3) == 0 || this.canMelt(world, pos, 4)) {
            int i = world.dimension() == Level.END ? world.getBrightness(LightLayer.BLOCK, pos) : world.getMaxLocalRawBrightness(pos);
            if (i > 11 - (Integer)state.getValue(AGE) - state.getLightBlock() && this.increaseAge(state, world, pos)) {
                BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

                for (Direction direction : Direction.values()) {
                    mutable.setWithOffset(pos, direction);
                    BlockState blockState = world.getBlockState(mutable);
                    if (blockState.is(this) && !this.increaseAge(blockState, world, mutable)) {
                        world.scheduleTick(mutable, this, Mth.nextInt(random, 20, 40));
                    }
                }

                return;
            }
        }

        world.scheduleTick(pos, this, Mth.nextInt(random, 20, 40));
    }

    private boolean increaseAge(BlockState state, Level world, BlockPos pos) {
        int i = (Integer)state.getValue(AGE);
        if (i < 3) {
            world.setBlock(pos, state.setValue(AGE, i + 1), Block.UPDATE_CLIENTS);
            return false;
        } else {
            this.melt(state, world, pos);
            return true;
        }
    }

    private BlockState getMeltedState() {
        return Blocks.LAVA.defaultBlockState();
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(world, player, pos, state, blockEntity, tool);
        BlockState blockState = world.getBlockState(pos.below());
        if (blockState.blocksMotion() || blockState.liquid()) {
            world.setBlockAndUpdate(pos, getMeltedState());
        }
    }

    protected void melt(BlockState state, Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, getMeltedState());
        world.neighborChanged(pos, getMeltedState().getBlock(), null);
    }

    private boolean canMelt(BlockGetter world, BlockPos pos, int maxNeighbors) {
        int i = 0;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            mutable.setWithOffset(pos, direction);
            if (world.getBlockState(mutable).is(this)) {
                if (++i >= maxNeighbors) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        return ItemStack.EMPTY;
    }
}
