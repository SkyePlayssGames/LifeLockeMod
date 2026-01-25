package com.galaxyy.lifelocke.entity.ai;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class PathfindHelper {
    public static int findNearestFloor(Level level, BlockPos blockPos) {
        int i = 0;
        while (level.getBlockState(blockPos.below(i)).is(BlockTags.AIR) && blockPos.below(i).getY() > -64) {
            i += 1;
        }
        return i;
    }

    public static Vec3 makeSpeedVec(PathfinderMob mob, double wantedX, double wantedY, double wantedZ, double speed_modifier) {
        double speed = mob.getAttributeValue(Attributes.FLYING_SPEED);

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

    private static ArrayList<BlockPos> getChecks(Mob mob, int distance) {
        BlockPos start = mob.blockPosition();

        ArrayList<BlockPos> toCheck = new ArrayList<>();
        ArrayList<BlockPos> eastWestToDo = new ArrayList<>();
        ArrayList<BlockPos> upDownToDo = new ArrayList<>();
        eastWestToDo.add(start);

        for (int i = 1; i <= distance; i++) {
            eastWestToDo.add(start.north(i));
            eastWestToDo.add(start.south(i));
        }

        for (BlockPos blockPos : eastWestToDo) {
            for (int i = 1; i <= distance; i++) {
                upDownToDo.add(blockPos.east(i));
            }
            for (int i = 1; i <= distance; i++) {
                upDownToDo.add(blockPos.west(i));
            }
            upDownToDo.add(blockPos);
        }

        for (BlockPos blockPos : upDownToDo) {
            for (int i = 1; i <= distance; i++) {
                toCheck.add(blockPos.above(i));
            }
            for (int i = 1; i <= distance; i++) {
                toCheck.add(blockPos.below(i));
            }
            toCheck.add(blockPos);
        }


        return toCheck;
    }

    private static ArrayList<BlockPos> check(ArrayList<BlockPos> toCheck, TagKey<Block> blockTag, Level world) {
        ArrayList<BlockPos> toReturn = new ArrayList<>();
        for (BlockPos blockPos : toCheck) {
            if (world.getBlockState(blockPos).is(blockTag)) {
                toReturn.add(blockPos);
            }
        }
        return toReturn;
    }

    private static float abs_sqrt(int a) {
        return abs_sqrt(((float) a));
    }

    private static float abs_sqrt(float a) {
        return Mth.sqrt(Mth.abs(a));
    }

    private static HashMap<BlockPos, Float> getDistances(ArrayList<BlockPos> toCalculate, BlockPos origin) {
        HashMap<BlockPos, Float> distances = new HashMap<>();

        for (BlockPos blockPos : toCalculate) {
            float xz = Mth.square(abs_sqrt(blockPos.getX() - origin.getX()) + abs_sqrt(blockPos.getZ() - origin.getZ()));
            float xyz = Mth.square(abs_sqrt(blockPos.getY() - origin.getY()) + abs_sqrt(xz));
            distances.put(blockPos, xyz);
        }

        return distances;
    }

    public static BlockPos getLowestDistance(HashMap<BlockPos, Float> distances) {
        BlockPos result = null;
        float lowest = Float.POSITIVE_INFINITY;
        for (BlockPos blockPos : distances.keySet()) {
            if (lowest > distances.get(blockPos)) {
                result = blockPos;
                lowest = distances.get(blockPos);
            }
        }
        return result;
    }

    public static BlockPos findNearbyBlock(Mob mob, TagKey<Block> blockTag, int distance) {
        ArrayList<BlockPos> toCheck = getChecks(mob, distance);
        ArrayList<BlockPos> checked = check(toCheck, blockTag, mob.level());
        HashMap<BlockPos, Float> distances = getDistances(checked, mob.blockPosition());
        BlockPos toGo = getLowestDistance(distances);

        if (toGo == null) { return null; }

        return new BlockPos(toGo.getX(), toGo.getY() + 1, toGo.getZ());
    }

    public static boolean isTouchingBlock(Mob mob, TagKey<Block> blockTag) {
        Level world = mob.level();
        return world.getBlockState(mob.blockPosition()).is(blockTag);
    }
}
