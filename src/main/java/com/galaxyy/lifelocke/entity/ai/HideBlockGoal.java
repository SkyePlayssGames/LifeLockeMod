package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;

import static com.galaxyy.lifelocke.entity.ai.BlockFinder.findNearbyBlock;
import static com.galaxyy.lifelocke.entity.ai.BlockFinder.isTouchingBlock;

public class HideBlockGoal extends Goal {
    private final MobEntity mob;
    private final TagKey<Block> blockTag;
    private final int distance;
    private final double speed;

    public HideBlockGoal(MobEntity mob, TagKey<Block> blockTag, double speed, int distance) {
        this.mob = mob;
        this.blockTag = blockTag;
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        if (this.mob.hasControllingPassenger()) { return false; }
        return findNearbyBlock(this.mob, this.blockTag, this.distance) != null || isTouchingBlock(this.mob, this.blockTag);
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
}
