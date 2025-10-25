package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> ELECTRIC = registerStatusEffect("electric",
            new ElectricEffect(StatusEffectCategory.BENEFICIAL, 0xfae43c));
    public static final RegistryEntry<StatusEffect> FIGHTING = registerStatusEffect("fighting",
            new FightingEffect(StatusEffectCategory.BENEFICIAL, 0xd44e2c).addAttributeModifier(
                    EntityAttributes.GENERIC_ATTACK_SPEED, Identifier.of(LifeLocke.MOD_ID, "fighting"),
                    100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final RegistryEntry<StatusEffect> WATER = registerStatusEffect("water",
            new WaterEffect(StatusEffectCategory.BENEFICIAL, 0x70d2ff));
    public static final RegistryEntry<StatusEffect> NORMAL = registerStatusEffect("normal",
            new NormalEffect(StatusEffectCategory.BENEFICIAL, 0xe1e8eb));
    public static final RegistryEntry<StatusEffect> FIRE = registerStatusEffect("fire",
            new FireEffect(StatusEffectCategory.BENEFICIAL, 0xeb761c));
    public static final RegistryEntry<StatusEffect> GRASS = registerStatusEffect("grass",
            new GrassEffect(StatusEffectCategory.BENEFICIAL, 0x1ceb34));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LifeLocke.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        System.out.println("Registering Mod Effects for " + LifeLocke.MOD_ID);
    }
}
