package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.event.AddTooltipsEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class EnergyBottleItem extends Item {
    public enum EffectTime {
        HALF_AN_HOUR,
        HOUR;

        public int lengthTicks() {
            return switch(this) {
                case HALF_AN_HOUR -> 36000;
                case HOUR -> 72000;
            };
        }

        public Component tooltip() {
            return switch(this) {
                case HALF_AN_HOUR -> Component.literal("(00:30:00)");
                case HOUR -> Component.literal("(01:00:00)");
            };
        }
    }

    public EnergyBottleItem(Properties settings, Holder<MobEffect> type, EffectTime time) {
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
