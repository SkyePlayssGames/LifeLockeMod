package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.LifeLocke;
import jdk.jshell.Snippet;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;

import java.util.Locale;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> ELECTRIC = registerStatusEffect("electric",
            new ElectricEffect(StatusEffectCategory.BENEFICIAL, 0xfae43c));
    public static final RegistryEntry<StatusEffect> FIGHTING = registerStatusEffect("fighting",
            new FightingEffect(StatusEffectCategory.BENEFICIAL, 0xd44e2c).addAttributeModifier(
                    EntityAttributes.ATTACK_SPEED, Identifier.of(LifeLocke.MOD_ID, "fighting"),
                    2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final RegistryEntry<StatusEffect> WATER = registerStatusEffect("water",
            new WaterEffect(StatusEffectCategory.BENEFICIAL, 0x70d2ff));
    public static final RegistryEntry<StatusEffect> NORMAL = registerStatusEffect("normal",
            new CheckedEffect(StatusEffectCategory.BENEFICIAL, 0xe1e8eb));
    public static final RegistryEntry<StatusEffect> FIRE = registerStatusEffect("fire",
            new FireEffect(StatusEffectCategory.BENEFICIAL, 0xeb761c));
    public static final RegistryEntry<StatusEffect> GRASS = registerStatusEffect("grass",
            new CheckedEffect(StatusEffectCategory.BENEFICIAL, 0x1ceb34));
    public static final RegistryEntry<StatusEffect> ICE = registerStatusEffect("ice",
            new IceEffect(StatusEffectCategory.BENEFICIAL, 0x4dfff0));
    public static final RegistryEntry<StatusEffect> POISON = registerStatusEffect("poison",
            new PoisonEffect(StatusEffectCategory.BENEFICIAL, 0x3517a3));
    public static final RegistryEntry<StatusEffect> GROUND = registerStatusEffect("ground",
            new CheckedEffect(StatusEffectCategory.BENEFICIAL, 0xa37017));
    public static final RegistryEntry<StatusEffect> FLYING = registerStatusEffect("flying",
            new FlyingEffect(StatusEffectCategory.BENEFICIAL, 0xa7e8df));
    public static final RegistryEntry<StatusEffect> BUG = registerStatusEffect("bug",
            new BugEffect(StatusEffectCategory.BENEFICIAL, 0xbcf542).addAttributeModifier(
                    EntityAttributes.SCALE, Identifier.of(LifeLocke.MOD_ID, "bug"),
                    -0.4, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ).addAttributeModifier(
                    EntityAttributes.FALL_DAMAGE_MULTIPLIER, Identifier.of(LifeLocke.MOD_ID, "bug"),
                    -1, EntityAttributeModifier.Operation.ADD_VALUE
            ).addAttributeModifier(
                    EntityAttributes.MAX_HEALTH, Identifier.of(LifeLocke.MOD_ID, "bug"),
                    -4, EntityAttributeModifier.Operation.ADD_VALUE
            ));
    public static final RegistryEntry<StatusEffect> ROCK = registerStatusEffect("rock",
            new RockEffect(StatusEffectCategory.BENEFICIAL, 0xab7333));
    public static final RegistryEntry<StatusEffect> GHOST = registerStatusEffect("ghost",
            new GhostEffect(StatusEffectCategory.BENEFICIAL, 0x791a9c));
    public static final RegistryEntry<StatusEffect> DRAGON = registerStatusEffect("dragon",
            new DragonEffect(StatusEffectCategory.BENEFICIAL, 0x312696).addAttributeModifier(
                    EntityAttributes.SCALE, Identifier.of(LifeLocke.MOD_ID, "dragon"),
                    0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ).addAttributeModifier(
                    EntityAttributes.MAX_HEALTH, Identifier.of(LifeLocke.MOD_ID, "dragon"),
                    4, EntityAttributeModifier.Operation.ADD_VALUE
            ));
    public static final RegistryEntry<StatusEffect> STEEL = registerStatusEffect("steel",
            new SteelEffect(StatusEffectCategory.BENEFICIAL, 0x9fb3b2));
    public static final RegistryEntry<StatusEffect> FAIRY = registerStatusEffect("fairy",
            new CheckedEffect(StatusEffectCategory.BENEFICIAL, 0xdf76e3));
    public static final RegistryEntry<StatusEffect> DARK = registerStatusEffect("dark",
            new DarkEffect(StatusEffectCategory.BENEFICIAL, 0x000000));
    public static final RegistryEntry<StatusEffect> PSYCHIC = registerStatusEffect("psychic",
            new PsychicEffect(StatusEffectCategory.BENEFICIAL, 0xca67e0));
    public static final RegistryEntry<StatusEffect> CURSE_TYPE = registerStatusEffect("curse_type",
            new CheckedEffect(StatusEffectCategory.BENEFICIAL, 0x4a40e3));

    public static final RegistryEntry[] EFFECTS = {NORMAL, ELECTRIC, FIGHTING, WATER, FIRE, GRASS, ICE, POISON, GROUND, FLYING, BUG,
            ROCK, GHOST, DRAGON, STEEL, FAIRY, DARK, PSYCHIC, CURSE_TYPE};

    public static final RegistryEntry[] ROLLABLE_EFFECTS = {NORMAL, ELECTRIC, FIGHTING, WATER, FIRE, GRASS, ICE, POISON, GROUND, FLYING, BUG,
            ROCK, GHOST, DRAGON, STEEL, FAIRY, DARK, PSYCHIC};


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LifeLocke.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        System.out.println("Registering Mod Effects for " + LifeLocke.MOD_ID);
    }
}
