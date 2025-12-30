package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> FAIRY_HEAL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fairy_heal"));

    public static final RegistryKey<DamageType> PLANT_ATTACK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "plant_attack"));


    public static final RegistryKey<DamageType> BUG = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "bug"));
    public static final RegistryKey<DamageType> FIRE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fire"));
    public static final RegistryKey<DamageType> DARK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "dark"));
    public static final RegistryKey<DamageType> WATER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "water"));
    public static final RegistryKey<DamageType> GROUND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "ground"));
    public static final RegistryKey<DamageType> ROCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "rock"));
    public static final RegistryKey<DamageType> ICE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "ice"));
    public static final RegistryKey<DamageType> PSYCHIC = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "psychic"));
    public static final RegistryKey<DamageType> FLYING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "flying"));
    public static final RegistryKey<DamageType> POISON = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "poison"));
    public static final RegistryKey<DamageType> GRASS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "grass"));
    public static final RegistryKey<DamageType> ELECTRIC = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "electric"));
    public static final RegistryKey<DamageType> FIGHTING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fighting"));
    public static final RegistryKey<DamageType> GHOST = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "ghost"));
    public static final RegistryKey<DamageType> STEEL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "steel"));
    public static final RegistryKey<DamageType> CURSE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "curse_type"));
    public static final RegistryKey<DamageType> DRAGON = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "dragon"));
    public static final RegistryKey<DamageType> FAIRY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fairy"));


    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getEntryOrThrow(key));
    }
    public static DamageSource of(World world, RegistryKey<DamageType> key, Entity attacker) {
        return new DamageSource(world.getRegistryManager().getEntryOrThrow(key), attacker);
    }
}
