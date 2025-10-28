package com.galaxyy.lifelocke.block;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DUMMY_BLOCK = registerBlock("dummy_block", new Block(
            AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(LifeLocke.MOD_ID, "dummy_block"))).breakInstantly()
    ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LifeLocke.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LifeLocke.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LifeLocke.MOD_ID, "dummy_block")))));
    }

    public static void registerModBlocks() {
        System.out.println("Registering Mod Blocks for " + LifeLocke.MOD_ID);
    }
}
