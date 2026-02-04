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
                        entries.accept(ModItems.FIRE_BOTTLES.get("regular"));
                        entries.accept(ModItems.FIRE_BOTTLES.get("burst"));
                        entries.accept(ModItems.FIRE_BOTTLES.get("extended"));
                        entries.accept(ModItems.GRASS_BOTTLES.get("regular"));
                        entries.accept(ModItems.GRASS_BOTTLES.get("burst"));
                        entries.accept(ModItems.GRASS_BOTTLES.get("extended"));
                        entries.accept(ModItems.GHOST_BOTTLES.get("regular"));
                        entries.accept(ModItems.GHOST_BOTTLES.get("burst"));
                        entries.accept(ModItems.GHOST_BOTTLES.get("extended"));
                        entries.accept(ModItems.PSYCHIC_BOTTLES.get("regular"));
                        entries.accept(ModItems.PSYCHIC_BOTTLES.get("burst"));
                        entries.accept(ModItems.PSYCHIC_BOTTLES.get("extended"));
                        entries.accept(ModItems.FIRE_ENERGY);
                        entries.accept(ModItems.GRASS_ENERGY);
                        entries.accept(ModItems.GHOST_ENERGY);
                        entries.accept(ModItems.PSYCHIC_ENERGY);
                        entries.accept(ModBlocks.TERA_TRIAL_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        System.out.println("Registering Item Groups for " + LifeLocke.MOD_ID);
    }
}
