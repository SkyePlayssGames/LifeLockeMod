package com.galaxyy.lifelocke.item.consumable_component;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModConsumableComponents {
    public static final ConsumableComponent FIRE_CONSUMABLE_COMPONENT = ConsumableComponents.drink()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.FIRE, 36000))
    ).build();

    public static void registerConsumableComponents() {
        System.out.println("Registering Consumable Components for " + LifeLocke.MOD_ID);
    }
}
