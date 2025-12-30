package com.galaxyy.lifelocke.entity.ai;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockFinder {
    private static ArrayList<BlockPos> getChecks(MobEntity mob, int distance) {
        BlockPos start = mob.getBlockPos();

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
                toCheck.add(blockPos.up(i));
            }
            for (int i = 1; i <= distance; i++) {
                toCheck.add(blockPos.down(i));
            }
            toCheck.add(blockPos);
        }


        return toCheck;
    }

    private static ArrayList<BlockPos> check(ArrayList<BlockPos> toCheck, ImmutableList<BlockState> blockStates, World world) {
        ArrayList<BlockPos> toReturn = new ArrayList<>();
        for (BlockPos blockPos : toCheck) {
            if (blockStates.contains(world.getBlockState(blockPos))) {
                toReturn.add(blockPos);
            }
        }
        return toReturn;
    }

    private static float abs_sqrt(int a) {
        return abs_sqrt(((float) a));
    }

    private static float abs_sqrt(float a) {
        return MathHelper.sqrt(MathHelper.abs(a));
    }

    private static HashMap<BlockPos, Float> getDistances(ArrayList<BlockPos> toCalculate, BlockPos origin) {
        HashMap<BlockPos, Float> distances = new HashMap<>();

        for (BlockPos blockPos : toCalculate) {
            float xz = MathHelper.square(abs_sqrt(blockPos.getX() - origin.getX()) + abs_sqrt(blockPos.getZ() - origin.getZ()));
            float xyz = MathHelper.square(abs_sqrt(blockPos.getY() - origin.getY()) + abs_sqrt(xz));
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

    public static BlockPos findNearbyBlock(MobEntity mob, ImmutableList<BlockState> blockStates, int distance) {
        ArrayList<BlockPos> toCheck = getChecks(mob, distance);
        ArrayList<BlockPos> checked = check(toCheck, blockStates, mob.getEntityWorld());
        HashMap<BlockPos, Float> distances = getDistances(checked, mob.getBlockPos());
        BlockPos toGo = getLowestDistance(distances);

        if (toGo == null) { return null; }

        return new BlockPos(toGo.getX(), toGo.getY() + 1, toGo.getZ());
    }

    public static boolean isTouchingBlock(MobEntity mob, ImmutableList<BlockState> blockStates) {
        World world = mob.getEntityWorld();
        return blockStates.contains(world.getBlockState(mob.getBlockPos()));
    }
}
