package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.effect.ModEffects;
import java.util.HashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> FAIRY_HEAL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fairy_heal"));

    public static final ResourceKey<DamageType> PLANT_ATTACK = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "plant_attack"));

    public static final HashMap<Holder<MobEffect>, ResourceKey<DamageType>> SLAIN_DAMAGE_TYPES = new HashMap<>();
    public static final ResourceKey<DamageType> BUG = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/bug"));
    public static final ResourceKey<DamageType> FIRE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/fire"));
    public static final ResourceKey<DamageType> DARK = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/dark"));
    public static final ResourceKey<DamageType> WATER = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/water"));
    public static final ResourceKey<DamageType> GROUND = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/ground"));
    public static final ResourceKey<DamageType> ROCK = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/rock"));
    public static final ResourceKey<DamageType> ICE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/ice"));
    public static final ResourceKey<DamageType> PSYCHIC = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/psychic"));
    public static final ResourceKey<DamageType> FLYING = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/flying"));
    public static final ResourceKey<DamageType> POISON = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/poison"));
    public static final ResourceKey<DamageType> GRASS = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/grass"));
    public static final ResourceKey<DamageType> ELECTRIC = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/electric"));
    public static final ResourceKey<DamageType> FIGHTING = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/fighting"));
    public static final ResourceKey<DamageType> GHOST = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/ghost"));
    public static final ResourceKey<DamageType> STEEL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/steel"));
    public static final ResourceKey<DamageType> CURSE_TYPE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/curse_type"));
    public static final ResourceKey<DamageType> DRAGON = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/dragon"));
    public static final ResourceKey<DamageType> FAIRY = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "slain/fairy"));

    public static final HashMap<Holder<MobEffect>, ResourceKey<DamageType>> PROJ_DAMAGE_TYPES = new HashMap<>();
    public static final ResourceKey<DamageType> BUG_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/bug"));
    public static final ResourceKey<DamageType> FIRE_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/fire"));
    public static final ResourceKey<DamageType> DARK_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/dark"));
    public static final ResourceKey<DamageType> WATER_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/water"));
    public static final ResourceKey<DamageType> GROUND_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/ground"));
    public static final ResourceKey<DamageType> ROCK_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/rock"));
    public static final ResourceKey<DamageType> ICE_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/ice"));
    public static final ResourceKey<DamageType> PSYCHIC_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/psychic"));
    public static final ResourceKey<DamageType> FLYING_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/flying"));
    public static final ResourceKey<DamageType> POISON_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/poison"));
    public static final ResourceKey<DamageType> GRASS_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/grass"));
    public static final ResourceKey<DamageType> ELECTRIC_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/electric"));
    public static final ResourceKey<DamageType> FIGHTING_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/fighting"));
    public static final ResourceKey<DamageType> GHOST_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/ghost"));
    public static final ResourceKey<DamageType> STEEL_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/steel"));
    public static final ResourceKey<DamageType> CURSE_TYPE_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/curse_type"));
    public static final ResourceKey<DamageType> DRAGON_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/dragon"));
    public static final ResourceKey<DamageType> FAIRY_PROJ = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "proj/fairy"));

    public static final HashMap<Holder<MobEffect>, ResourceKey<DamageType>> FALL_DAMAGE_TYPES = new HashMap<>();
    public static final ResourceKey<DamageType> BUG_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/bug"));
    public static final ResourceKey<DamageType> FIRE_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/fire"));
    public static final ResourceKey<DamageType> DARK_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/dark"));
    public static final ResourceKey<DamageType> WATER_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/water"));
    public static final ResourceKey<DamageType> GROUND_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/ground"));
    public static final ResourceKey<DamageType> ROCK_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/rock"));
    public static final ResourceKey<DamageType> ICE_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/ice"));
    public static final ResourceKey<DamageType> PSYCHIC_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/psychic"));
    public static final ResourceKey<DamageType> FLYING_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/flying"));
    public static final ResourceKey<DamageType> POISON_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/poison"));
    public static final ResourceKey<DamageType> GRASS_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/grass"));
    public static final ResourceKey<DamageType> ELECTRIC_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/electric"));
    public static final ResourceKey<DamageType> FIGHTING_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/fighting"));
    public static final ResourceKey<DamageType> GHOST_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/ghost"));
    public static final ResourceKey<DamageType> STEEL_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/steel"));
    public static final ResourceKey<DamageType> CURSE_TYPE_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/curse_type"));
    public static final ResourceKey<DamageType> DRAGON_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/dragon"));
    public static final ResourceKey<DamageType> FAIRY_FALL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fall/fairy"));


    public static DamageSource of(Level world, ResourceKey<DamageType> key) {
        return new DamageSource(world.registryAccess().getOrThrow(key));
    }
    public static DamageSource of(Level world, ResourceKey<DamageType> key, Entity attacker) {
        return new DamageSource(world.registryAccess().getOrThrow(key), attacker);
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
