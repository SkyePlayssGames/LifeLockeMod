package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> ELECTRIC = registerStatusEffect("electric",
            new ElectricEffect(StatusEffectCategory.BENEFICIAL, 0xfae43c));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LifeLocke.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        System.out.println("Registering Mod Effects for " + LifeLocke.MOD_ID);
    }
}
