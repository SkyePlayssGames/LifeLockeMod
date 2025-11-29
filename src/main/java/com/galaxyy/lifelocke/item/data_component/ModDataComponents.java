package com.galaxyy.lifelocke.item.data_component;

import com.galaxyy.lifelocke.LifeLocke;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

public class ModDataComponents {
    public static final ComponentType<Unit> STEEL = Registry.register(
            Registries.DATA_COMPONENT_TYPE, Identifier.of(LifeLocke.MOD_ID, "steel"),
            ComponentType.<Unit>builder().codec(Codec.unit(Unit.INSTANCE)).build()
    );
    public static final ComponentType<Unit> FLYING = Registry.register(
            Registries.DATA_COMPONENT_TYPE, Identifier.of(LifeLocke.MOD_ID, "flying"),
            ComponentType.<Unit>builder().codec(Codec.unit(Unit.INSTANCE)).build()
    );

    public static void registerModDataComponents() {
        System.out.println("Registering Mod Data Components for " + LifeLocke.MOD_ID);
    }
}
