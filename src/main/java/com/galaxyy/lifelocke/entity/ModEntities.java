package com.galaxyy.lifelocke.entity;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.FireMobEntity;
import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> FIRE_MOB_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fire_mob"));
    public static final ResourceKey<EntityType<?>> GRASS_MOB_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "grass_mob"));

    public static final EntityType<FireMobEntity> FIRE_MOB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fire_mob"),
            EntityType.Builder.of(FireMobEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.6f)
                    .fireImmune()
                    .build(FIRE_MOB_KEY)
    );

    public static final EntityType<GrassMobEntity> GRASS_MOB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "grass_mob"),
            EntityType.Builder.of(GrassMobEntity::new, MobCategory.MONSTER)
                    .sized(0.85f, 1.3f)
                    .build(GRASS_MOB_KEY)
    );

    public static void registerModEntities() {
        System.out.println("Registering Mod Entities for " + LifeLocke.MOD_ID);
    }
}
