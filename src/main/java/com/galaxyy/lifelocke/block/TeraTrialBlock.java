package com.galaxyy.lifelocke.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jspecify.annotations.Nullable;

public class TeraTrialBlock extends BaseEntityBlock {
    public enum TeraTrialType implements StringRepresentable {
        NULL,
        FIRE;

        @Override
        public String getSerializedName() {
            switch (this) {
                case NULL: return "null";
                case FIRE: return "fire";
                default: return "null";
            }
        }
    }

    public static final EnumProperty<TeraTrialType> TYPE = EnumProperty.create("type", TeraTrialType.class);

    protected TeraTrialBlock(Properties properties) {
        super(properties);

        defaultBlockState().setValue(TYPE, TeraTrialType.NULL);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(TeraTrialBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TeraTrialBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlocks.TERA_TRIAL_BLOCK_ENTITY, TeraTrialBlockEntity::tick);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }
}
