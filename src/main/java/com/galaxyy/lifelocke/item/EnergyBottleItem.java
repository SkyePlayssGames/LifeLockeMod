package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.event.AddTooltipsEvent;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class EnergyBottleItem extends Item {
    public enum EffectTime {
        HALF_AN_HOUR
    }

    public static int effectTimeToTicks(EffectTime time) {
        return switch(time) {
            case HALF_AN_HOUR -> 36000;
        };
    }

    public static Text effectTimeToText(EffectTime time) {
        return switch(time) {
            case HALF_AN_HOUR -> Text.literal("(00:30:00)");
        };
    }

    public EnergyBottleItem(Settings settings, RegistryEntry<StatusEffect> type, EffectTime time) {
        super(settings.food(new FoodComponent(0, 0, true), getConsumableComponent(type, time)));
        AddTooltipsEvent.append_tooltip(this, Text.translatable(
                "tooltip.lifelocke.energy_bottle",
                type.value().getName(),
                effectTimeToText(time)
                )
                .formatted(Formatting.BLUE)
        );
    }


    public static ConsumableComponent getConsumableComponent(RegistryEntry<StatusEffect> type, EffectTime time) {
        return ConsumableComponents.drink().consumeEffect(new ApplyEffectsConsumeEffect(
                new StatusEffectInstance(type, effectTimeToTicks(time))
        )).build();
    }

}
