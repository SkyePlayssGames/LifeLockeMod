package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.HashMap;

public class GroundTrigger implements BlockUseConsumer {
    private enum SUMMONABLE_ORES {
        COAL,
        COPPER,
        IRON,
        GOLD,
        REDSTONE,
        LAPIS,
        DIAMOND,
        NETHERITE,
        EMERALD,
        AMETHYST
    }

    private static final HashMap<SUMMONABLE_ORES, Block> ORE_MAP = new HashMap<>();

    private static final int AMOUNT_OF_ORES = SUMMONABLE_ORES.values().length;

    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
                || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return;
        }

        if (!playerEntity.isSneaking()) {
            world.setBlockState(playerEntity.getBlockPos().down(), Blocks.DIRT.getDefaultState());
            HungerCost.takeHunger(playerEntity, 0.25f);
        } else {
            BlockPos center = playerEntity.getBlockPos().down();
            BlockPos[] blocks = {
                    center.north().west(), center.north(), center.north().east(),
                    center.west(), center, center.east(),
                    center.south().west(), center.south(), center.south().east()
            };

            for (BlockPos blockPos : blocks) {
                world.setBlockState(blockPos, getRandomOreBlock(playerEntity).getDefaultState());
            }

            HungerCost.takeHunger(playerEntity, 1);
        }
    }

    private Block getRandomOreBlock(PlayerEntity playerEntity) {
        if (playerEntity.getRandom().nextInt(100) != 0) {
            return Blocks.STONE;
        }
        int random = playerEntity.getRandom().nextBetweenExclusive(0, AMOUNT_OF_ORES);
        HungerCost.takeHunger(playerEntity, 1);
        return ORE_MAP.get(Arrays.stream(SUMMONABLE_ORES.values()).toArray()[random]);
    }

    public static void registerOreMap() {
        ORE_MAP.put(SUMMONABLE_ORES.COAL, Blocks.COAL_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.COPPER, Blocks.COPPER_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.IRON, Blocks.IRON_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.GOLD, Blocks.GOLD_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.REDSTONE, Blocks.REDSTONE_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.LAPIS, Blocks.LAPIS_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.DIAMOND, Blocks.DIAMOND_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.EMERALD, Blocks.EMERALD_ORE);
        ORE_MAP.put(SUMMONABLE_ORES.NETHERITE, Blocks.ANCIENT_DEBRIS);
        ORE_MAP.put(SUMMONABLE_ORES.AMETHYST, Blocks.AMETHYST_BLOCK);
    }
}
