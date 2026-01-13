package com.galaxyy.lifelocke.block;

import com.galaxyy.lifelocke.LifeLocke;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
