package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.event.AddTooltipsEvent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class EnergyBottleItem extends Item {
    public EnergyBottleItem(Settings settings, StatusEffectInstance statusEffectInstance, String time) {
        super(settings);
        AddTooltipsEvent.append_tooltip(this, Text.translatable(
                "tooltip.lifelocke.energy_bottle",
                statusEffectInstance.getEffectType().value().getName(),
                time
                )
                .formatted(Formatting.BLUE)
        );
    }


}
