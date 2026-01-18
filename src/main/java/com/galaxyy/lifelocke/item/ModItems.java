package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;

import java.util.HashMap;
import java.util.function.Function;

public class ModItems {
    public static final Item DUMMY_ITEM = registerItem("dummy_item", DummyItem::new);

    public static final HashMap<String, EnergyBottleItem> FIRE_BOTTLES = EnergyBottleItem.energyBottleGroup(
            new Item.Properties(), ModEffects.FIRE, LifeLocke.MOD_ID, "fire_bottle"
    );

    public static final HashMap<String, EnergyBottleItem> GRASS_BOTTLES = EnergyBottleItem.energyBottleGroup(
            new Item.Properties(), ModEffects.GRASS, LifeLocke.MOD_ID, "grass_bottle"
    );

    public static final HashMap<String, EnergyBottleItem> GHOST_BOTTLES = EnergyBottleItem.energyBottleGroup(
            new Item.Properties(), ModEffects.GHOST, LifeLocke.MOD_ID, "ghost_bottle"
    );


    public static final Item FIRE_MOB_SPAWN_EGG = registerSpawnEgg(ModEntities.FIRE_MOB);
    public static final Item GRASS_MOB_SPAWN_EGG = registerSpawnEgg(ModEntities.GRASS_MOB);
    public static final Item GHOST_MOB_SPAWN_EGG = registerSpawnEgg(ModEntities.GHOST_MOB);

    public static final Item FIRE_ENERGY = registerItem("fire_energy", Item::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        Identifier id = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name);
        return Registry.register(BuiltInRegistries.ITEM, id,
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }

    private static Item registerSpawnEgg(EntityType<?> type) {
        return Items.registerItem(
                ResourceKey.create(Registries.ITEM, EntityType.getKey(type).withSuffix("_spawn_egg")), SpawnEggItem::new, new Item.Properties().spawnEgg(type)
        );
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + LifeLocke.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register((itemgroup) -> {
                itemgroup.accept(FIRE_MOB_SPAWN_EGG);
                itemgroup.accept(GRASS_MOB_SPAWN_EGG);
                itemgroup.accept(GHOST_MOB_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.OP_BLOCKS).register(itemgroup -> {
                itemgroup.accept(DUMMY_ITEM);
                itemgroup.accept(ModBlocks.DUMMY_BLOCK);
        });
    }
}
