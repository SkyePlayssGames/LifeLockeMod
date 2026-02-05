package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.effect.Types;
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
                        assert Types.FIRE_TYPE.bottles.isPresent();
                        assert Types.GRASS_TYPE.bottles.isPresent();
                        assert Types.GHOST_TYPE.bottles.isPresent();
                        assert Types.PSYCHIC_TYPE.bottles.isPresent();

                        entries.accept(Types.FIRE_TYPE.bottles.get().regular);
                        entries.accept(Types.FIRE_TYPE.bottles.get().extended);
                        entries.accept(Types.FIRE_TYPE.bottles.get().burst);
                        entries.accept(Types.GRASS_TYPE.bottles.get().regular);
                        entries.accept(Types.GRASS_TYPE.bottles.get().extended);
                        entries.accept(Types.GRASS_TYPE.bottles.get().burst);
                        entries.accept(Types.GHOST_TYPE.bottles.get().regular);
                        entries.accept(Types.GHOST_TYPE.bottles.get().extended);
                        entries.accept(Types.GHOST_TYPE.bottles.get().burst);
                        entries.accept(Types.PSYCHIC_TYPE.bottles.get().regular);
                        entries.accept(Types.PSYCHIC_TYPE.bottles.get().extended);
                        entries.accept(Types.PSYCHIC_TYPE.bottles.get().burst);
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
