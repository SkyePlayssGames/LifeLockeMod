package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.entity.ModEntities;
import com.galaxyy.lifelocke.item.consumable_component.ModConsumableComponents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TypedEntityData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item DUMMY_ITEM = registerItem("dummy_item", Item::new);

    public static final Item FIRE_BOTTLE = registerItem("fire_bottle", settings ->
            new Item(settings.food(new FoodComponent(0, 0, true), ModConsumableComponents.FIRE_CONSUMABLE_COMPONENT)));


    public static final Item FIRE_MOB_SPAWN_EGG = registerSpawnEgg(ModEntities.FIRE_MOB);

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        Identifier id = Identifier.of(LifeLocke.MOD_ID, name);
        return Registry.register(Registries.ITEM, id,
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id))));
    }

    private static Item registerSpawnEgg(EntityType<?> type) {
        return Items.register(
                RegistryKey.of(RegistryKeys.ITEM, EntityType.getId(type).withSuffixedPath("_spawn_egg")), SpawnEggItem::new, new Item.Settings().spawnEgg(type)
        );
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + LifeLocke.MOD_ID);
    }
}
