package com.galaxyy.lifelocke.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(ModTags.FIRE_MOB_HEAL)
                .addOptionalTag(BlockTags.FIRE);

        valueLookupBuilder(ModTags.GRASS_MOB_HIDE)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.TALL_DRY_GRASS)
                .add(Blocks.FERN)
                .add(Blocks.LARGE_FERN)
                .add(Blocks.SUNFLOWER)
                .add(Blocks.ROSE_BUSH)
                .add(Blocks.PEONY)
                .add(Blocks.PITCHER_PLANT)
                .add(Blocks.LILAC);

        valueLookupBuilder(ModTags.GRASS_MOB_ATTACK)
                .addOptionalTag(BlockTags.DIRT)
                .addOptionalTag(BlockTags.LEAVES);
    }
}
