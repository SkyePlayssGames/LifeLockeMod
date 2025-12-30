package com.galaxyy.lifelocke.entity.ai;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

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

    public static BlockPos findNearbyBlock(MobEntity mob, ImmutableList<BlockState> blockStates, int distance) {
        World world = mob.getEntityWorld();
        ArrayList<BlockPos> toCheck = getChecks(mob, distance);
        for (BlockPos blockPos : toCheck) {
            if (blockStates.contains(world.getBlockState(blockPos))) {
                return blockPos;
            }
        }
        return null;
    }

    public static boolean isTouchingBlock(MobEntity mob, ImmutableList<BlockState> blockStates) {
        World world = mob.getEntityWorld();
        return blockStates.contains(world.getBlockState(mob.getBlockPos()));
    }
}
