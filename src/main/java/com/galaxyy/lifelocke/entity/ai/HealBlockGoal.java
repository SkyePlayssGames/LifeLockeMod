package com.galaxyy.lifelocke.entity.ai;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.tag.TagKey;
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
    private final TagKey<Block> blockTag;

    public HealBlockGoal(MobEntity mob, double speed, TagKey<Block> blockState) {
        this(mob, speed, blockState, 5);
    }

    public HealBlockGoal(MobEntity mob, double speed, TagKey<Block> blockState, int hpDifference) {
        this(mob, speed, blockState, hpDifference, 8);
    }

    public HealBlockGoal(MobEntity mob, double speed, TagKey<Block> blockStates, int hpDifference, int distance) {
        this.mob = mob;
        this.speed = speed;
        this.blockTag = blockStates;
        this.distance = distance;
        this.hpDifference = hpDifference;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }



    @Override
    public boolean canStart() {
        if (this.mob.hasControllingPassenger()) { return false; }
        return (this.mob.getHealth() < this.mob.getMaxHealth() - this.hpDifference && findNearbyBlock(this.mob, this.blockTag, this.distance) != null) || (this.mob.getHealth() < this.mob.getMaxHealth() && isTouchingBlock(this.mob, this.blockTag));
    }

    @Override
    public void start() {
        if (!isTouchingBlock(this.mob, this.blockTag)) {
            BlockPos blockPos = findNearbyBlock(this.mob, this.blockTag, this.distance);
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