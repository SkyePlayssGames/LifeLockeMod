package com.galaxyy.lifelocke.entity.ai;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.EnumSet;

import static com.galaxyy.lifelocke.entity.ai.BlockFinder.findNearbyBlock;
import static com.galaxyy.lifelocke.entity.ai.BlockFinder.isTouchingBlock;

public class HealBlockGoal extends Goal {
    private final MobEntity mob;
    private final double speed;
    private final int distance;
    private final int hpDifference;
    private final ImmutableList<BlockState> blockStates;

    public HealBlockGoal(MobEntity mob, double speed, BlockState blockState) {
        this(mob, speed, blockState, 5);
    }

    public HealBlockGoal(MobEntity mob, double speed, ImmutableList<BlockState> blockStates) {
        this(mob, speed, blockStates, 5);
    }

    public HealBlockGoal(MobEntity mob, double speed, BlockState blockState, int hpDifference) {
        this(mob, speed, blockState, hpDifference, 8);
    }

    public HealBlockGoal(MobEntity mob, double speed, ImmutableList<BlockState> blockStates, int hpDifference) {
        this(mob, speed, blockStates, hpDifference, 8);
    }

    public HealBlockGoal(MobEntity mob, double speed, BlockState blockState, int hpDifference, int distance) {
        this(mob, speed, ImmutableList.of(blockState), hpDifference, distance);
    }

    public HealBlockGoal(MobEntity mob, double speed, ImmutableList<BlockState> blockStates, int hpDifference, int distance) {
        this.mob = mob;
        this.speed = speed;
        this.blockStates = blockStates;
        this.distance = distance;
        this.hpDifference = hpDifference;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
        System.out.println(blockStates.toString());
    }



    @Override
    public boolean canStart() {
        if (this.mob.hasControllingPassenger()) { return false; }
        return (this.mob.getHealth() < this.mob.getMaxHealth() - this.hpDifference && findNearbyBlock(this.mob, this.blockStates, this.distance) != null) || (this.mob.getHealth() < this.mob.getMaxHealth() && isTouchingBlock(this.mob, this.blockStates));
    }

    @Override
    public void start() {
        if (!isTouchingBlock(this.mob, this.blockStates)) {
            BlockPos blockPos = findNearbyBlock(this.mob, this.blockStates, this.distance);
            if (blockPos == null) {
                return;
            }
            this.mob.getNavigation().startMovingTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), this.speed);
        }
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle() && !this.mob.hasControllingPassenger();
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }
}