package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class RandomFlyAroundGoal extends Goal {
    private final PathfinderMob mob;
    private final double odds;
    private final int timeout;
    private final int maxDistanceOffGround;

    private double wantedX;
    private double wantedY;
    private double wantedZ;

    private int ticksRun;


    public RandomFlyAroundGoal(PathfinderMob pathfinderMob) {
        this(pathfinderMob, 0.02);
    }

    public RandomFlyAroundGoal(PathfinderMob pathfinderMob, double odds) {
        this(pathfinderMob, odds, 6);
    }

    public RandomFlyAroundGoal(PathfinderMob pathfinderMob, double odds, int maxDistanceOffGround) {
        this(pathfinderMob, odds, maxDistanceOffGround, 200);
    }

    public RandomFlyAroundGoal(PathfinderMob pathfinderMob, double odds, int maxDistanceOffGround, int timeout) {
        this.mob = pathfinderMob;
        this.odds = odds;
        this.maxDistanceOffGround = maxDistanceOffGround;
        this.timeout = timeout;
        this.setFlags(EnumSet.of(Flag.MOVE));
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

        return checkCloseEnoughToGround(BlockPos.containing(wantedX, wantedY, wantedZ));
    }

    private boolean checkCloseEnoughToGround(BlockPos pos) {
        boolean result = false;
        for (int i = 1; i <= this.maxDistanceOffGround; ++i) {
            if (this.mob.level().getBlockState(pos.below(i)).getBlock() != Blocks.AIR) {
                result = true;
            }
        }
        return result;
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
        mob.setDeltaMovement(makeSpeedVec(1.0f));
    }

    @Override
    public void stop() {
        mob.setDeltaMovement(makeSpeedVec(0.25f));
    }

    private Vec3 makeSpeedVec(double speed_modifier) {
        double speed = this.mob.getAttributeValue(Attributes.FLYING_SPEED);

        double x = 0;
        double y = 0;
        double z = 0;

        if (wantedX > mob.getX()) {
            x = speed * speed_modifier;
        } else if (wantedX < mob.getX()) {
            x = -speed * speed_modifier;
        }
        if (wantedY > mob.getY()) {
            y = speed * speed_modifier;
        }  else if (wantedY < mob.getY()) {
            y = -speed * speed_modifier;
        }
        if (wantedZ > mob.getZ()) {
            z = speed * speed_modifier;
        } else if (wantedZ < mob.getZ()) {
            z = -speed * speed_modifier;
        }

        return new Vec3(x, y, z);
    }
}
