package com.galaxyy.lifelocke.damage;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> FAIRY_HEAL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            Identifier.of(LifeLocke.MOD_ID, "fairy_heal"));
    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getEntryOrThrow(key));
    }
}
