package com.galaxyy.lifelocke.entity.ai;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;

import static com.galaxyy.lifelocke.entity.ai.BlockFinder.findNearbyBlock;
import static com.galaxyy.lifelocke.entity.ai.BlockFinder.isTouchingBlock;

public class HealBlockGoal extends Goal {
    private final Mob mob;
    private final double speed;
    private final int distance;
    private final int hpDifference;
    private final TagKey<Block> blockTag;

    public HealBlockGoal(Mob mob, double speed, TagKey<Block> blockState) {
        this(mob, speed, blockState, 5);
    }

    public HealBlockGoal(Mob mob, double speed, TagKey<Block> blockState, int hpDifference) {
        this(mob, speed, blockState, hpDifference, 8);
    }

    public HealBlockGoal(Mob mob, double speed, TagKey<Block> blockStates, int hpDifference, int distance) {
        this.mob = mob;
        this.speed = speed;
        this.blockTag = blockStates;
        this.distance = distance;
        this.hpDifference = hpDifference;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }



    @Override
    public boolean canUse() {
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
            this.mob.getNavigation().moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), this.speed);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone() && !this.mob.hasControllingPassenger();
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }
}