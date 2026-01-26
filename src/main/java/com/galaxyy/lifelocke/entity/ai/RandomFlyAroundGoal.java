package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

import static com.galaxyy.lifelocke.entity.ai.PathfindHelper.findNearestFloor;
import static com.galaxyy.lifelocke.entity.ai.PathfindHelper.makeSpeedVec;

public class RandomFlyAroundGoal extends Goal {
    private final FlyingMonster mob;
    private final double odds;
    private final int timeout;
    private final int maxDistanceOffGround;

    private double wantedX;
    private double wantedY;
    private double wantedZ;

    private int ticksRun;


    public RandomFlyAroundGoal(FlyingMonster flyingMonster) {
        this(flyingMonster, 0.02);
    }

    public RandomFlyAroundGoal(FlyingMonster flyingMonster, double odds) {
        this(flyingMonster, odds, 6);
    }

    public RandomFlyAroundGoal(FlyingMonster flyingMonster, double odds, int maxDistanceOffGround) {
        this(flyingMonster, odds, maxDistanceOffGround, 200);
    }

    public RandomFlyAroundGoal(FlyingMonster flyingMonster, double odds, int maxDistanceOffGround, int timeout) {
        this.mob = flyingMonster;
        this.odds = odds;
        this.maxDistanceOffGround = maxDistanceOffGround;
        this.timeout = timeout;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        RandomSource random = this.mob.getRandom();

        if (this.mob.hasControllingPassenger()) {
            return false;
        }
        if (random.nextDouble() > this.odds) {
            return false;
        }

        this.wantedX = this.mob.getX() + random.nextInt(-8, 8);
        this.wantedY = this.mob.getY() + random.nextInt(-3, 5);
        this.wantedZ = this.mob.getZ() + random.nextInt(-8, 8);

        if (this.mob.level().getBlockState(BlockPos.containing(wantedX, wantedY, wantedZ)) != Blocks.AIR.defaultBlockState()) {
            return false;
        }

        return checkCloseEnoughToGround(this.mob.level(), BlockPos.containing(wantedX, wantedY, wantedZ));
    }

    private boolean checkCloseEnoughToGround(Level level, BlockPos pos) {
        return findNearestFloor(level, pos) < maxDistanceOffGround;
    }

    @Override
    public void start() {
        this.ticksRun = 0;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.ticksRun > this.timeout) {
            return false;
        }

        return this.mob.distanceToSqr(wantedX, wantedY, wantedZ) > 1;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        ticksRun++;
        mob.setTargetSpeed(makeSpeedVec(mob, wantedX, wantedY, wantedZ, 1.0f));
    }

    @Override
    public void stop() {
        mob.setTargetSpeed(Vec3.ZERO);
    }


}
