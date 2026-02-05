package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.LifeLocke;
import jdk.jshell.Snippet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.Locale;

public class ModEffects {
    public static final MobEffect ELECTRIC = new ElectricEffect(MobEffectCategory.BENEFICIAL, 0xfae43c);
    public static final MobEffect FIGHTING = new FightingEffect(MobEffectCategory.BENEFICIAL, 0xd44e2c)
            .addAttributeModifier(
                    Attributes.ATTACK_SPEED, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fighting"),
                    2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            );

    public static final MobEffect WATER = new WaterEffect(MobEffectCategory.BENEFICIAL, 0x70d2ff);
    public static final MobEffect NORMAL = new CheckedEffect(MobEffectCategory.BENEFICIAL, 0xe1e8eb);
    public static final MobEffect FIRE = new FireEffect(MobEffectCategory.BENEFICIAL, 0xeb761c);
    public static final MobEffect GRASS = new CheckedEffect(MobEffectCategory.BENEFICIAL, 0x1ceb34);
    public static final MobEffect ICE = new IceEffect(MobEffectCategory.BENEFICIAL, 0x4dfff0);
    public static final MobEffect POISON = new PoisonEffect(MobEffectCategory.BENEFICIAL, 0x3517a3);
    public static final MobEffect GROUND = new CheckedEffect(MobEffectCategory.BENEFICIAL, 0xa37017);
    public static final MobEffect FLYING = new FlyingEffect(MobEffectCategory.BENEFICIAL, 0xa7e8df);
    public static final MobEffect BUG = new BugEffect(MobEffectCategory.BENEFICIAL, 0xbcf542)
            .addAttributeModifier(
                    Attributes.SCALE, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "bug"),
                    -0.4, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ).addAttributeModifier(
                    Attributes.FALL_DAMAGE_MULTIPLIER, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "bug"),
                    -2.0f/3.0f, AttributeModifier.Operation.ADD_VALUE
            ).addAttributeModifier(
                    Attributes.MAX_HEALTH, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "bug"),
                    -4, AttributeModifier.Operation.ADD_VALUE
            );

    public static final MobEffect ROCK = new RockEffect(MobEffectCategory.BENEFICIAL, 0xab7333);
    public static final MobEffect GHOST = new GhostEffect(MobEffectCategory.BENEFICIAL, 0x791a9c);
    public static final MobEffect DRAGON = new DragonEffect(MobEffectCategory.BENEFICIAL, 0x312696)
            .addAttributeModifier(
                    Attributes.SCALE, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "dragon"),
                    0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ).addAttributeModifier(
                    Attributes.MAX_HEALTH, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "dragon"),
                    4, AttributeModifier.Operation.ADD_VALUE
            );

    public static final MobEffect STEEL = new SteelEffect(MobEffectCategory.BENEFICIAL, 0x9fb3b2);
    public static final MobEffect FAIRY = new CheckedEffect(MobEffectCategory.BENEFICIAL, 0xdf76e3);
    public static final MobEffect DARK = new DarkEffect(MobEffectCategory.BENEFICIAL, 0x000000);
    public static final MobEffect PSYCHIC = new PsychicEffect(MobEffectCategory.BENEFICIAL, 0xca67e0);
    public static final MobEffect CURSE_TYPE = new CheckedEffect(MobEffectCategory.BENEFICIAL, 0x4a40e3);

    public static final Holder<MobEffect> STUCK = registerStatusEffect("stuck",
            new StuckEffect(MobEffectCategory.HARMFUL, 0xab7333)
                    .addAttributeModifier(Attributes.GRAVITY, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "stuck"), -0.08, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "stuck"), -0.42, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final Holder<MobEffect> IN_TRIAL = registerStatusEffect("in_trial",
            new CheckedEffect(MobEffectCategory.HARMFUL, 4866583));


    private static Holder<MobEffect> registerStatusEffect(String name, MobEffect statusEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        System.out.println("Registering Mod Effects for " + LifeLocke.MOD_ID);
    }
}
