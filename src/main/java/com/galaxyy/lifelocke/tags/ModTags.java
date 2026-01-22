package com.galaxyy.lifelocke.tags;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> FIRE_MOB_HEAL = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(
            LifeLocke.MOD_ID, "entity/fire_mob/heal"
    ));

    public static final TagKey<Block> GRASS_MOB_HIDE = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(
            LifeLocke.MOD_ID, "entity/grass_mob/hide"
    ));
    public static final TagKey<Block> GRASS_MOB_ATTACK = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(
            LifeLocke.MOD_ID, "entity/grass_mob/attack"
    ));
    public static final TagKey<EntityType<?>> FLYING_MOBS = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(
            LifeLocke.MOD_ID, "flying_mobs"
    ));
}
