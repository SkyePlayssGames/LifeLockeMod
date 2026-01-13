package com.galaxyy.lifelocke.entity.ai;

import static com.galaxyy.lifelocke.entity.ai.BlockFinder.findNearbyBlock;
import static com.galaxyy.lifelocke.entity.ai.BlockFinder.isTouchingBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;

public class HideBlockGoal extends Goal {
    private final Mob mob;
    private final TagKey<Block> blockTag;
    private final int distance;
    private final double speed;

    public HideBlockGoal(Mob mob, TagKey<Block> blockTag, double speed, int distance) {
        this.mob = mob;
        this.blockTag = blockTag;
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        if (this.mob.hasControllingPassenger()) { return false; }
        return findNearbyBlock(this.mob, this.blockTag, this.distance) != null || isTouchingBlock(this.mob, this.blockTag);
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
}
