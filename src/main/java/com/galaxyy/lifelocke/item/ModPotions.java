package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final Holder<Potion> LEVITATION_POTION = registerPotion("levitation",
            new Potion("levitation", new MobEffectInstance(MobEffects.LEVITATION, 3600, 0)));

    private static Holder<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name), potion
        );
    }

    public static void registerModPotions() {
        System.out.println("Registering Mod Potions for " + LifeLocke.MOD_ID);
    }
}
