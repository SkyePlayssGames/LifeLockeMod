package com.galaxyy.lifelocke.power;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

public class RockPower implements PlayerBlockBreakEvents.After {
    Map<BlockState, Item> ores = Map.ofEntries(
            Map.entry(Blocks.IRON_ORE.getDefaultState(), Items.RAW_IRON),
            Map.entry(Blocks.DEEPSLATE_IRON_ORE.getDefaultState(), Items.RAW_IRON),
            Map.entry(Blocks.COAL_ORE.getDefaultState(), Items.COAL),
            Map.entry(Blocks.DEEPSLATE_COAL_ORE.getDefaultState(), Items.COAL),
            Map.entry(Blocks.REDSTONE_ORE.getDefaultState(), Items.REDSTONE),
            Map.entry(Blocks.DEEPSLATE_REDSTONE_ORE.getDefaultState(), Items.REDSTONE),
            Map.entry(Blocks.LAPIS_ORE.getDefaultState(), Items.LAPIS_LAZULI),
            Map.entry(Blocks.DEEPSLATE_LAPIS_ORE.getDefaultState(), Items.LAPIS_LAZULI),
            Map.entry(Blocks.GOLD_ORE.getDefaultState(), Items.RAW_GOLD),
            Map.entry(Blocks.DEEPSLATE_GOLD_ORE.getDefaultState(), Items.RAW_GOLD),
            Map.entry(Blocks.DIAMOND_ORE.getDefaultState(), Items.DIAMOND),
            Map.entry(Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState(), Items.DIAMOND),
            Map.entry(Blocks.EMERALD_ORE.getDefaultState(), Items.EMERALD),
            Map.entry(Blocks.DEEPSLATE_EMERALD_ORE.getDefaultState(), Items.EMERALD),
            Map.entry(Blocks.COPPER_ORE.getDefaultState(), Items.RAW_COPPER),
            Map.entry(Blocks.DEEPSLATE_COPPER_ORE.getDefaultState(), Items.RAW_COPPER)
    );
    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (Arrays.stream(ores.keySet().toArray()).anyMatch(Predicate.isEqual(state)) && blockEntity != null) {
            EntityType.ITEM.spawnFromItemStack(((ServerWorld) world), new ItemStack(ores.get(state), 1),
                    player, blockEntity.getPos(), SpawnReason.TRIGGERED, false, false);
        }
    }
}
