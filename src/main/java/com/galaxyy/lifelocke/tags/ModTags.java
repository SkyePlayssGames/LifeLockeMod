package com.galaxyy.lifelocke.tags;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Block> FIRE_MOB_HEAL = TagKey.of(RegistryKeys.BLOCK, Identifier.of(
            LifeLocke.MOD_ID, "entity/fire_mob/heal"
    ));

    public static final TagKey<Block> GRASS_MOB_HIDE = TagKey.of(RegistryKeys.BLOCK, Identifier.of(
            LifeLocke.MOD_ID, "entity/grass_mob/hide"
    ));
    public static final TagKey<Block> GRASS_MOB_ATTACK = TagKey.of(RegistryKeys.BLOCK, Identifier.of(
            LifeLocke.MOD_ID, "entity/grass_mob/attack"
    ));
}
