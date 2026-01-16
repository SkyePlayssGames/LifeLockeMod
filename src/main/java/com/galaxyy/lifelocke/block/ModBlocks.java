package com.galaxyy.lifelocke.block;

import com.galaxyy.lifelocke.LifeLocke;
import java.util.function.Function;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Block DUMMY_BLOCK = registerBlock("dummy_block", settings ->
            new Block(settings
                    .instabreak()
                    .mapColor(MapColor.COLOR_PINK)
                    .instrument(NoteBlockInstrument.CREEPER)
            ));
    public static final Block FROSTED_OBSIDIAN = registerBlockWithoutBlockItem("frosted_obsidian", settings ->
            new FrostedObsidianBlock(settings
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(50.0F, 1200.0F)
    ));

    public static final Block TERA_TRIAL_BLOCK = registerBlock("tera_trial_block", settings ->
            new TeraTrialBlock(settings
                    // .mapColor(TODO)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(-1, 1200)
                    .lightLevel(state -> 15)));

    public static final BlockEntityType<TeraTrialBlockEntity> TERA_TRIAL_BLOCK_ENTITY = registerBlockEntity(
            "tera_trial_block_entity", TeraTrialBlockEntity::new, TERA_TRIAL_BLOCK
    );




    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(
            String name, FabricBlockEntityTypeBuilder.Factory<? extends T> factory, Block block
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id,
                FabricBlockEntityTypeBuilder.<T>create(factory, block).build()
        );
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Identifier id = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name);
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(
                Registries.BLOCK, id
        )));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, id, toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Identifier id = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name);
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(
                Registries.BLOCK, id
        )));
        return Registry.register(BuiltInRegistries.BLOCK, id, toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name);
        Registry.register(BuiltInRegistries.ITEM, id,
                new BlockItem(block, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }

    public static void registerModBlocks() {
        System.out.println("Registering Mod Blocks for " + LifeLocke.MOD_ID);
    }
}
