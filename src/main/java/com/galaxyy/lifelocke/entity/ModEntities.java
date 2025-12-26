package com.galaxyy.lifelocke.entity;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.FireMobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> FIRE_MOB_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fire_mob"));

    public static final EntityType<FireMobEntity> FIRE_MOB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fire_mob"),
            EntityType.Builder.create(FireMobEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.8f, 0.6f)
                    .build(FIRE_MOB_KEY)
    );

    public static void registerModEntities() {
        System.out.println("Registering Mod Entities for " + LifeLocke.MOD_ID);
    }
}
