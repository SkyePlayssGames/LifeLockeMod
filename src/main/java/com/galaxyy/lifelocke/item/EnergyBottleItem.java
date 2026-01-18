package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.event.AddTooltipsEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.HashMap;

public class EnergyBottleItem extends Item {
    public enum EffectTime {
        MINUTE,
        HALF_AN_HOUR,
        HOUR;

        public int lengthTicks() {
            return switch(this) {
                case MINUTE -> 1200;
                case HALF_AN_HOUR -> 36000;
                case HOUR -> 72000;
            };
        }

        public Component tooltip() {
            return switch(this) {
                case MINUTE -> Component.literal("(00:01:00)");
                case HALF_AN_HOUR -> Component.literal("(00:30:00)");
                case HOUR -> Component.literal("(01:00:00)");
            };
        }
    }

    public static HashMap<String, EnergyBottleItem> energyBottleGroup(
            Properties settings, Holder<MobEffect> type, String namespace, String base_name) {
        Identifier regular_id = Identifier.fromNamespaceAndPath(namespace, base_name);
        Identifier burst_id = Identifier.fromNamespaceAndPath(namespace, "burst_" + base_name);
        Identifier extended_id = Identifier.fromNamespaceAndPath(namespace, "long_" + base_name);

        EnergyBottleItem regular = new EnergyBottleItem(settings.setId(
                ResourceKey.create(Registries.ITEM, regular_id)), type, EffectTime.HALF_AN_HOUR);

        EnergyBottleItem burst = new EnergyBottleItem(settings.setId(
                ResourceKey.create(Registries.ITEM, burst_id)), type, EffectTime.MINUTE);

        EnergyBottleItem extended = new EnergyBottleItem(settings.setId(
                ResourceKey.create(Registries.ITEM, extended_id)), type, EffectTime.HOUR);

        Registry.register(BuiltInRegistries.ITEM, regular_id, regular);
        Registry.register(BuiltInRegistries.ITEM, burst_id, burst);
        Registry.register(BuiltInRegistries.ITEM, extended_id, extended);

        HashMap<String, EnergyBottleItem> result = new HashMap<>();
        result.put("regular", regular);
        result.put("burst", burst);
        result.put("extended", extended);

        return result;
    }

    private EnergyBottleItem(Properties settings, Holder<MobEffect> type, EffectTime time) {
        super(settings.food(new FoodProperties(0, 0, true), getConsumableComponent(type, time)));
        AddTooltipsEvent.append_tooltip(this, Component.translatable(
                "tooltip.lifelocke.energy_bottle",
                type.value().getDisplayName(),
                time.tooltip()
                )
                .withStyle(ChatFormatting.BLUE)
        );
    }


    public static Consumable getConsumableComponent(Holder<MobEffect> type, EffectTime time) {
        return Consumables.defaultDrink().onConsume(new ApplyStatusEffectsConsumeEffect(
                new MobEffectInstance(type, time.lengthTicks())
        )).build();
    }

}
