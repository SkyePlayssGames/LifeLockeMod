package com.galaxyy.lifelocke.item.data_component;

import com.galaxyy.lifelocke.LifeLocke;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

public class ModDataComponents {
    public static final DataComponentType<Unit> STEEL = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "steel"),
            DataComponentType.<Unit>builder().persistent(Unit.CODEC).build()
    );
    public static final DataComponentType<Unit> FLYING = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "flying"),
            DataComponentType.<Unit>builder().persistent(Unit.CODEC).build()
    );

    public static void registerModDataComponents() {
        System.out.println("Registering Mod Data Components for " + LifeLocke.MOD_ID);
    }
}
