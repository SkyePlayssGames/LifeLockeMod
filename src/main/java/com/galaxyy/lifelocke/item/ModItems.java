package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.LifeLocke;
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
import java.util.function.Function;

public class ModItems {
    public static final Item DUMMY_ITEM = registerItem("dummy_item", DummyItem::new);

    public static final Item FIRE_BOTTLE = registerItem("fire_bottle", settings ->
            new EnergyBottleItem(settings, ModEffects.FIRE, EnergyBottleItem.EffectTime.HALF_AN_HOUR)
    );
    public static final Item LONG_FIRE_BOTTLE = registerItem("long_fire_bottle", settings ->
            new EnergyBottleItem(settings, ModEffects.FIRE, EnergyBottleItem.EffectTime.HOUR)
    );

    public static final Item GRASS_BOTTLE = registerItem("grass_bottle", settings ->
            new EnergyBottleItem(settings, ModEffects.GRASS, EnergyBottleItem.EffectTime.HALF_AN_HOUR)
    );
    public static final Item LONG_GRASS_BOTTLE = registerItem("long_grass_bottle", settings ->
            new EnergyBottleItem(settings, ModEffects.GRASS, EnergyBottleItem.EffectTime.HOUR)
    );


    public static final Item FIRE_MOB_SPAWN_EGG = registerSpawnEgg(ModEntities.FIRE_MOB);
    public static final Item GRASS_MOB_SPAWN_EGG = registerSpawnEgg(ModEntities.GRASS_MOB);

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
        });
    }
}
