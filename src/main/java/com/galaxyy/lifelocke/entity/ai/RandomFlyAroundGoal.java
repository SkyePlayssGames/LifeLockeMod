package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import static com.galaxyy.lifelocke.entity.ai.PathfindHelper.findNearestFloor;

public class RandomFlyAroundGoal extends RandomStrollGoal {
    private final int maxDistanceOffGround;

    public RandomFlyAroundGoal(PathfinderMob pathfinderMob, double d, int maxDistanceOffGround) {
        super(pathfinderMob, d, 20);
        this.maxDistanceOffGround = maxDistanceOffGround;
    }

    public Vec3 psychicPosition() {
        RandomSource random = this.mob.getRandom();

        this.wantedX = this.mob.getX() + random.nextInt(-8, 8);
        this.wantedY = this.mob.getY() + random.nextInt(-3, 5);
        this.wantedZ = this.mob.getZ() + random.nextInt(-8, 8);

        if (this.mob.level().getBlockState(BlockPos.containing(wantedX, wantedY, wantedZ)) != Blocks.AIR.defaultBlockState()) {
            return null;
        }

        Vec3 vec = new Vec3(wantedX, wantedY, wantedZ);

        return checkCloseEnoughToGround(this.mob.level(), BlockPos.containing(vec)) ? vec : null;
    }

    private boolean checkCloseEnoughToGround(Level level, BlockPos pos) {
        return findNearestFloor(level, pos) < maxDistanceOffGround;
    }

    @Override
    protected @Nullable Vec3 getPosition() {
        return psychicPosition();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse();
    }
}
