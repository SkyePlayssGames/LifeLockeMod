package com.galaxyy.lifelocke.block;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.mojang.serialization.DataResult;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TrialSpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.ArrayList;

public class TeraTrialBlockEntity extends BlockEntity {
    private final ArrayList<BlockPos> spawnerOffsets = new ArrayList<>();

    private static BlockPos add(BlockPos pos, BlockPos other) {
        return new BlockPos(pos.getX() + other.getX(), pos.getY() + other.getY(), pos.getZ() + other.getZ());
    }

    public ArrayList<BlockPos> getSpawnerPositions() {
        ArrayList<BlockPos> spawnerPositions = new ArrayList<>();
        for (BlockPos blockPos : this.spawnerOffsets) {
            spawnerPositions.add(add(blockPos, this.worldPosition));
        }

        return spawnerPositions;
    }

    public void appendSpawnerOffset(BlockPos blockPos) {
        spawnerOffsets.add(blockPos);
    }

    private static int[] encodeBlockPos(ArrayList<BlockPos> blockPosArrayList, int i) {
        DataResult<Tag> result;

        if (blockPosArrayList.size() <= i) {
            result = BlockPos.CODEC.encodeStart(NbtOps.INSTANCE, BlockPos.ZERO);
        } else if (blockPosArrayList.get(i) == null) {
            result = BlockPos.CODEC.encodeStart(NbtOps.INSTANCE, BlockPos.ZERO);
        } else {
            result = BlockPos.CODEC.encodeStart(NbtOps.INSTANCE, blockPosArrayList.get(i));
        }

        return result.getOrThrow().asIntArray().orElseThrow();
    }

    private static BlockPos decodeBlockPos(int[] ints) {
        return (ints[0] == 0 && ints[1] == 0 && ints[2] == 0) ? null : new BlockPos(ints[0], ints[1], ints[2]);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        for (int i = 0; i < 4; i++) {
            valueOutput.putIntArray("spawner" + i, encodeBlockPos(this.spawnerOffsets, i));
        }

        super.saveAdditional(valueOutput);
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.spawnerOffsets.clear();

        for (int i = 0; i < 4; i++) {
            BlockPos x = decodeBlockPos(valueInput.getIntArray("spawner" + i).orElse(new int[] {0, 0, 0}));
            if (x != null) this.spawnerOffsets.add(x);
        }
    }

    public TeraTrialBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.TERA_TRIAL_BLOCK_ENTITY, blockPos, blockState);
    }


    public static void tick(Level level, BlockPos blockPos, BlockState blockState, TeraTrialBlockEntity blockEntity) {
        if (level.isClientSide()) { return; }

        switch (blockState.getValue(TeraTrialBlock.TYPE)) {
            case NULL -> handleNullTick(level, blockPos, blockState, blockEntity);
            case FIRE -> handleFireTick(level, blockPos, blockState, blockEntity);
        }
    }

    private static boolean spawnersActive(Level level, TeraTrialBlockEntity blockEntity) {
        for (BlockPos blockPos : blockEntity.getSpawnerPositions()) {
            if (blockPos == null) {
                continue;
            } else if (!(level.getBlockState(blockPos).getBlock() instanceof TrialSpawnerBlock)) {
                continue;
            }
            if (level.getBlockState(blockPos).getValue(TrialSpawnerBlock.STATE) == TrialSpawnerState.ACTIVE) {
                return true;
            }
        }
        return false;
    }

    private static void handleNullTick(Level level, BlockPos blockPos, BlockState blockState, TeraTrialBlockEntity blockEntity) {
        return;
    }

    private static void handleFireTick(Level level, BlockPos blockPos, BlockState blockState, TeraTrialBlockEntity blockEntity) {
        if (!spawnersActive(level, blockEntity)) {
            return;
        }
        for (Player player : level.players()) {
            if (Mth.sqrt((float) player.distanceToSqr(blockPos.getCenter())) < 10) {
                player.addEffect(new MobEffectInstance(ModEffects.IN_TRIAL, 10, 1, false, false));
            }
        }
    }
}
