package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> FAIRY_HEAL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fairy_heal"));

    public static final RegistryKey<DamageType> PLANT_ATTACK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "plant_attack"));

    public static final HashMap<RegistryEntry<StatusEffect>, RegistryKey<DamageType>> SLAIN_DAMAGE_TYPES = new HashMap<>();
    public static final RegistryKey<DamageType> BUG = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/bug"));
    public static final RegistryKey<DamageType> FIRE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/fire"));
    public static final RegistryKey<DamageType> DARK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/dark"));
    public static final RegistryKey<DamageType> WATER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/water"));
    public static final RegistryKey<DamageType> GROUND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/ground"));
    public static final RegistryKey<DamageType> ROCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/rock"));
    public static final RegistryKey<DamageType> ICE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/ice"));
    public static final RegistryKey<DamageType> PSYCHIC = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/psychic"));
    public static final RegistryKey<DamageType> FLYING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/flying"));
    public static final RegistryKey<DamageType> POISON = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/poison"));
    public static final RegistryKey<DamageType> GRASS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/grass"));
    public static final RegistryKey<DamageType> ELECTRIC = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/electric"));
    public static final RegistryKey<DamageType> FIGHTING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/fighting"));
    public static final RegistryKey<DamageType> GHOST = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/ghost"));
    public static final RegistryKey<DamageType> STEEL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/steel"));
    public static final RegistryKey<DamageType> CURSE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/curse_type"));
    public static final RegistryKey<DamageType> DRAGON = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/dragon"));
    public static final RegistryKey<DamageType> FAIRY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "slain/fairy"));

    public static final HashMap<RegistryEntry<StatusEffect>, RegistryKey<DamageType>> PROJ_DAMAGE_TYPES = new HashMap<>();
    public static final RegistryKey<DamageType> BUG_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/bug"));
    public static final RegistryKey<DamageType> FIRE_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/fire"));
    public static final RegistryKey<DamageType> DARK_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/dark"));
    public static final RegistryKey<DamageType> WATER_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/water"));
    public static final RegistryKey<DamageType> GROUND_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/ground"));
    public static final RegistryKey<DamageType> ROCK_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/rock"));
    public static final RegistryKey<DamageType> ICE_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/ice"));
    public static final RegistryKey<DamageType> PSYCHIC_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/psychic"));
    public static final RegistryKey<DamageType> FLYING_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/flying"));
    public static final RegistryKey<DamageType> POISON_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/poison"));
    public static final RegistryKey<DamageType> GRASS_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/grass"));
    public static final RegistryKey<DamageType> ELECTRIC_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/electric"));
    public static final RegistryKey<DamageType> FIGHTING_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/fighting"));
    public static final RegistryKey<DamageType> GHOST_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/ghost"));
    public static final RegistryKey<DamageType> STEEL_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/steel"));
    public static final RegistryKey<DamageType> CURSE_TYPE_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/curse_type"));
    public static final RegistryKey<DamageType> DRAGON_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/dragon"));
    public static final RegistryKey<DamageType> FAIRY_PROJ = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "proj/fairy"));

    public static final HashMap<RegistryEntry<StatusEffect>, RegistryKey<DamageType>> FALL_DAMAGE_TYPES = new HashMap<>();
    public static final RegistryKey<DamageType> BUG_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/bug"));
    public static final RegistryKey<DamageType> FIRE_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/fire"));
    public static final RegistryKey<DamageType> DARK_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/dark"));
    public static final RegistryKey<DamageType> WATER_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/water"));
    public static final RegistryKey<DamageType> GROUND_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/ground"));
    public static final RegistryKey<DamageType> ROCK_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/rock"));
    public static final RegistryKey<DamageType> ICE_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/ice"));
    public static final RegistryKey<DamageType> PSYCHIC_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/psychic"));
    public static final RegistryKey<DamageType> FLYING_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/flying"));
    public static final RegistryKey<DamageType> POISON_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/poison"));
    public static final RegistryKey<DamageType> GRASS_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/grass"));
    public static final RegistryKey<DamageType> ELECTRIC_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/electric"));
    public static final RegistryKey<DamageType> FIGHTING_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/fighting"));
    public static final RegistryKey<DamageType> GHOST_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/ghost"));
    public static final RegistryKey<DamageType> STEEL_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/steel"));
    public static final RegistryKey<DamageType> CURSE_TYPE_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/curse_type"));
    public static final RegistryKey<DamageType> DRAGON_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/dragon"));
    public static final RegistryKey<DamageType> FAIRY_FALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fall/fairy"));


    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getEntryOrThrow(key));
    }
    public static DamageSource of(World world, RegistryKey<DamageType> key, Entity attacker) {
        return new DamageSource(world.getRegistryManager().getEntryOrThrow(key), attacker);
    }

    public static void registerSlainDamageTypes() {
        SLAIN_DAMAGE_TYPES.put(ModEffects.BUG, ModDamageTypes.BUG);
        SLAIN_DAMAGE_TYPES.put(ModEffects.FIRE, ModDamageTypes.FIRE);
        SLAIN_DAMAGE_TYPES.put(ModEffects.DARK, ModDamageTypes.DARK);
        SLAIN_DAMAGE_TYPES.put(ModEffects.WATER, ModDamageTypes.WATER);
        SLAIN_DAMAGE_TYPES.put(ModEffects.GROUND, ModDamageTypes.GROUND);
        SLAIN_DAMAGE_TYPES.put(ModEffects.ROCK, ModDamageTypes.ROCK);
        SLAIN_DAMAGE_TYPES.put(ModEffects.ICE, ModDamageTypes.ICE);
        SLAIN_DAMAGE_TYPES.put(ModEffects.PSYCHIC, ModDamageTypes.PSYCHIC);
        SLAIN_DAMAGE_TYPES.put(ModEffects.FLYING, ModDamageTypes.FLYING);
        SLAIN_DAMAGE_TYPES.put(ModEffects.POISON, ModDamageTypes.POISON);
        SLAIN_DAMAGE_TYPES.put(ModEffects.GRASS, ModDamageTypes.GRASS);
        SLAIN_DAMAGE_TYPES.put(ModEffects.ELECTRIC, ModDamageTypes.ELECTRIC);
        SLAIN_DAMAGE_TYPES.put(ModEffects.FIGHTING, ModDamageTypes.FIGHTING);
        SLAIN_DAMAGE_TYPES.put(ModEffects.GHOST, ModDamageTypes.GHOST);
        SLAIN_DAMAGE_TYPES.put(ModEffects.STEEL, ModDamageTypes.STEEL);
        SLAIN_DAMAGE_TYPES.put(ModEffects.CURSE_TYPE, ModDamageTypes.CURSE_TYPE);
        SLAIN_DAMAGE_TYPES.put(ModEffects.DRAGON, ModDamageTypes.DRAGON);
        SLAIN_DAMAGE_TYPES.put(ModEffects.FAIRY, ModDamageTypes.FAIRY);
    }

    public static void registerFallDamageTypes() {
        FALL_DAMAGE_TYPES.put(ModEffects.BUG, ModDamageTypes.BUG_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.FIRE, ModDamageTypes.FIRE_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.DARK, ModDamageTypes.DARK_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.WATER, ModDamageTypes.WATER_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.GROUND, ModDamageTypes.GROUND_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.ROCK, ModDamageTypes.ROCK_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.ICE, ModDamageTypes.ICE_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.PSYCHIC, ModDamageTypes.PSYCHIC_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.FLYING, ModDamageTypes.FLYING_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.POISON, ModDamageTypes.POISON_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.GRASS, ModDamageTypes.GRASS_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.ELECTRIC, ModDamageTypes.ELECTRIC_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.FIGHTING, ModDamageTypes.FIGHTING_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.GHOST, ModDamageTypes.GHOST_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.STEEL, ModDamageTypes.STEEL_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.CURSE_TYPE, ModDamageTypes.CURSE_TYPE_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.DRAGON, ModDamageTypes.DRAGON_FALL);
        FALL_DAMAGE_TYPES.put(ModEffects.FAIRY, ModDamageTypes.FAIRY_FALL);
    }

    public static void registerProjectileDamageTypes() {
        PROJ_DAMAGE_TYPES.put(ModEffects.BUG, ModDamageTypes.BUG_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.FIRE, ModDamageTypes.FIRE_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.DARK, ModDamageTypes.DARK_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.WATER, ModDamageTypes.WATER_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.GROUND, ModDamageTypes.GROUND_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.ROCK, ModDamageTypes.ROCK_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.ICE, ModDamageTypes.ICE_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.PSYCHIC, ModDamageTypes.PSYCHIC_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.FLYING, ModDamageTypes.FLYING_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.POISON, ModDamageTypes.POISON_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.GRASS, ModDamageTypes.GRASS_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.ELECTRIC, ModDamageTypes.ELECTRIC_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.FIGHTING, ModDamageTypes.FIGHTING_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.GHOST, ModDamageTypes.GHOST_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.STEEL, ModDamageTypes.STEEL_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.CURSE_TYPE, ModDamageTypes.CURSE_TYPE_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.DRAGON, ModDamageTypes.DRAGON_PROJ);
        PROJ_DAMAGE_TYPES.put(ModEffects.FAIRY, ModDamageTypes.FAIRY_PROJ);
    }
}
