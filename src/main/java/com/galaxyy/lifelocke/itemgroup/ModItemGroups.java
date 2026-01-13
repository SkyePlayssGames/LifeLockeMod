package com.galaxyy.lifelocke.itemgroup;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class ModItemGroups {
    public static final CreativeModeTab LIFELOCKE_STUFF = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "lifelocke_stuff"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(Blocks.BARRIER))
                    .title(Component.translatable("itemgroup.lifelocke.lifelocke_stuff"))
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.FIRE_BOTTLE);
                        entries.accept(ModItems.LONG_FIRE_BOTTLE);
                        entries.accept(ModItems.GRASS_BOTTLE);
                        entries.accept(ModItems.LONG_GRASS_BOTTLE);
                    }).build());

    public static void registerItemGroups() {
        System.out.println("Registering Item Groups for " + LifeLocke.MOD_ID);
    }
}
