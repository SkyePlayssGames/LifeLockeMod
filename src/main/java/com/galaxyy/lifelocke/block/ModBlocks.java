package com.galaxyy.lifelocke.block;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block DUMMY_BLOCK = registerBlock("dummy_block", settings ->
            new Block(settings
                    .breakInstantly()
                    .mapColor(MapColor.PINK)
                    .instrument(NoteBlockInstrument.CREEPER)
            ));
    public static final Block FROSTED_OBSIDIAN = registerBlockWithoutBlockItem("frosted_obsidian", settings ->
            new FrostedObsidianBlock(settings
                    .mapColor(MapColor.BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(50.0F, 1200.0F)
    ));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Identifier id = Identifier.of(LifeLocke.MOD_ID, name);
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(
                RegistryKeys.BLOCK, id
        )));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, id, toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        Identifier id = Identifier.of(LifeLocke.MOD_ID, name);
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(
                RegistryKeys.BLOCK, id
        )));
        return Registry.register(Registries.BLOCK, id, toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.of(LifeLocke.MOD_ID, name);
        Registry.register(Registries.ITEM, id,
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id))));
    }

    public static void registerModBlocks() {
        System.out.println("Registering Mod Blocks for " + LifeLocke.MOD_ID);
    }
}
