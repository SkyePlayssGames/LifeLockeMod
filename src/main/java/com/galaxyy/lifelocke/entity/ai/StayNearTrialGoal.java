package com.galaxyy.lifelocke.entity.ai;

import com.galaxyy.lifelocke.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

import static com.galaxyy.lifelocke.entity.ai.PathfindHelper.findNearbyBlock;

public class StayNearTrialGoal extends Goal {
    private final PathfinderMob mob;
    private final int maxSearchDistance;
    private final float minAwayDistance;
    private final float maxAwayDistance;
    private final float movementSpeedMod;

    public StayNearTrialGoal(PathfinderMob mob, int maxSearchDistance, float minAwayDistance, float maxAwayDistance, float movementSpeedMod) {
        this.mob = mob;
        this.maxSearchDistance = maxSearchDistance;
        this.maxAwayDistance = maxAwayDistance;
        this.minAwayDistance = minAwayDistance;
        this.movementSpeedMod = movementSpeedMod;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }


    @Override
    public boolean canUse() {
        if (this.mob.hasControllingPassenger()) return false;
        BlockPos trial = findNearbyBlock(mob, ModBlocks.TERA_TRIAL_BLOCK.getStateDefinition().getPossibleStates(), this.maxSearchDistance);
        if (trial == null) return false;
        return (Mth.sqrt((float) trial.distToCenterSqr(this.mob.position())) > maxAwayDistance);
    }

    @Override
    public void start() {
        BlockPos trial = findNearbyBlock(this.mob, ModBlocks.TERA_TRIAL_BLOCK.getStateDefinition().getPossibleStates(), this.maxSearchDistance);
        assert trial != null;
        this.mob.getNavigation().moveTo(trial.getX(), trial.getY(), trial.getZ(), this.movementSpeedMod);
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