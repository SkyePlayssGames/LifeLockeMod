package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import java.util.Arrays;
import java.util.HashMap;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class GroundTrigger implements ActivatedAbility {
    private enum SUMMONABLE_COMMON_ORES {
        COAL,
        COPPER,
        IRON,
        GOLD,
        REDSTONE,
        LAPIS,
        EMERALD,
        AMETHYST,
        QUARTZ,
        GOLD_NETHER
    }

    private enum SUMMONABLE_RARE_ORES {
        DIAMOND,
        NETHERITE
    }

    private enum SUMMONABLE_STONE_VARIANTS {
        DIORITE,
        GRANITE,
        ANDESITE,
        CALCITE,
        TUFF
    }

    private static final HashMap<SUMMONABLE_COMMON_ORES, Pair<Block, Block>> COMMON_ORE_MAP = new HashMap<>();
    private static final HashMap<SUMMONABLE_RARE_ORES, Pair<Block, Block>> RARE_ORE_MAP = new HashMap<>();
    private static final HashMap<SUMMONABLE_STONE_VARIANTS, Block> STONE_MAP = new HashMap<>();

    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())) {
            return false;
        }

        ServerLevel world = playerEntity.level();

        if (!playerEntity.isShiftKeyDown()) {
            world.setBlockAndUpdate(playerEntity.blockPosition().below(), Blocks.DIRT.defaultBlockState());
            HungerCost.takeHunger(playerEntity, 0.25f);
        } else {
            BlockPos center = playerEntity.blockPosition().below();
            BlockPos[] blocks = {
                    center.north().west(), center.north(), center.north().east(),
                    center.west(), center, center.east(),
                    center.south().west(), center.south(), center.south().east()
            };

            for (BlockPos blockPos : blocks) {
                world.setBlockAndUpdate(blockPos, getRandomOreBlock(playerEntity).defaultBlockState());
            }

            HungerCost.takeHunger(playerEntity, 1);
        }
        return true;
    }

    private Block getRandomOreBlock(Player playerEntity) {
        int stoneType = playerEntity.getRandom().nextInt(300);
        if (stoneType > 45) {
            return playerEntity.getRandom().nextBoolean() ? Blocks.STONE : Blocks.DEEPSLATE;
        } else if (stoneType > 6) {
            int random = playerEntity.getRandom().nextInt(0, SUMMONABLE_STONE_VARIANTS.values().length);
            HungerCost.takeHunger(playerEntity, 0.25f);
            return STONE_MAP.get(Arrays.stream(SUMMONABLE_STONE_VARIANTS.values()).toArray()[random]);
        } else if (stoneType > 0) {
            int random = playerEntity.getRandom().nextInt(0, SUMMONABLE_COMMON_ORES.values().length);
            HungerCost.takeHunger(playerEntity, 1);
            Pair<Block, Block> pair = COMMON_ORE_MAP.get(Arrays.stream(SUMMONABLE_COMMON_ORES.values()).toArray()[random]);
            return playerEntity.getRandom().nextBoolean() ? pair.getFirst() : pair.getSecond();
        } else {
            int random = playerEntity.getRandom().nextInt(0, SUMMONABLE_RARE_ORES.values().length);
            HungerCost.takeHunger(playerEntity, 4);
            Pair<Block, Block> pair = RARE_ORE_MAP.get(Arrays.stream(SUMMONABLE_RARE_ORES.values()).toArray()[random]);
            return playerEntity.getRandom().nextBoolean() ? pair.getFirst() : pair.getSecond();
        }
    }

    public static void registerGroundMaps() {
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.COAL, Pair.of(Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.COPPER, Pair.of(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.IRON, Pair.of(Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.GOLD, Pair.of(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.REDSTONE, Pair.of(Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.LAPIS, Pair.of(Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.EMERALD, Pair.of(Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.AMETHYST, Pair.of(Blocks.AMETHYST_BLOCK, Blocks.AMETHYST_BLOCK));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.QUARTZ, Pair.of(Blocks.NETHER_QUARTZ_ORE, Blocks.NETHER_QUARTZ_ORE));
        COMMON_ORE_MAP.put(SUMMONABLE_COMMON_ORES.GOLD_NETHER, Pair.of(Blocks.NETHER_GOLD_ORE, Blocks.NETHER_GOLD_ORE));

        RARE_ORE_MAP.put(SUMMONABLE_RARE_ORES.DIAMOND, Pair.of(Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE));
        RARE_ORE_MAP.put(SUMMONABLE_RARE_ORES.NETHERITE, Pair.of(Blocks.ANCIENT_DEBRIS, Blocks.NETHER_GOLD_ORE));

        STONE_MAP.put(SUMMONABLE_STONE_VARIANTS.DIORITE, Blocks.DIORITE);
        STONE_MAP.put(SUMMONABLE_STONE_VARIANTS.ANDESITE, Blocks.ANDESITE);
        STONE_MAP.put(SUMMONABLE_STONE_VARIANTS.GRANITE, Blocks.GRANITE);
        STONE_MAP.put(SUMMONABLE_STONE_VARIANTS.CALCITE, Blocks.CALCITE);
        STONE_MAP.put(SUMMONABLE_STONE_VARIANTS.TUFF, Blocks.TUFF);
    }
}
