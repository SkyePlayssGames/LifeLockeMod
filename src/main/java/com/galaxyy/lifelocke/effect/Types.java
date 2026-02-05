package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.item.EnergyBottleItem;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SHandler;
import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.ToggledAbility;
import com.galaxyy.lifelocke.triggers.activated.*;
import com.galaxyy.lifelocke.triggers.toggled.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;

public class Types {
    public static ArrayList<Holder<MobEffect>> TYPES = new ArrayList<>();
    public static ArrayList<Holder<MobEffect>> ROLLABLE_TYPES = new ArrayList<>();

    public static TypeContainer FIGHTING_TYPE = registerPassiveType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fighting"),
            ModEffects.FIGHTING, false, false
    );

    public static TypeContainer ELECTRIC_TYPE = registerToggledType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "electric"),
            ModEffects.ELECTRIC, false, false, new ElectricTrigger()
    );

    public static TypeContainer WATER_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "water"),
            ModEffects.WATER, false, false, new WaterTrigger()
    );

    public static TypeContainer NORMAL_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "normal"),
            ModEffects.NORMAL, false, false, new NormalTrigger()
    );

    public static TypeContainer FIRE_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fire"),
            ModEffects.FIRE, true, false, new FireTrigger()
    );

    public static TypeContainer GRASS_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "grass"),
            ModEffects.GRASS, true, false, new GrassTrigger()
    );

    public static TypeContainer ICE_TYPE = registerToggledType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "ice"),
            ModEffects.ICE, false, false, new IceTrigger()
    );

    public static TypeContainer POISON_TYPE = registerToggledType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "poison"),
            ModEffects.POISON, false, false, new PoisonTrigger()
    );

    public static TypeContainer GROUND_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "ground"),
            ModEffects.GROUND, false, false, new GroundTrigger()
    );

    public static TypeContainer FLYING_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "flying"),
            ModEffects.FLYING, false, false, new FlyingTrigger()
    );

    public static TypeContainer BUG_TYPE = registerPassiveType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "bug"),
            ModEffects.BUG, false, false
    );

    public static TypeContainer ROCK_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "rock"),
            ModEffects.ROCK, false, false, new RockTrigger()
    );

    public static TypeContainer GHOST_TYPE = registerToggledType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "ghost"),
            ModEffects.GHOST, true, false, new GhostTrigger()
    );

    public static TypeContainer DRAGON_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "dragon"),
            ModEffects.DRAGON, false, false, new DragonTrigger()
    );

    public static TypeContainer STEEL_TYPE = registerPassiveType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "steel"),
            ModEffects.STEEL, false, false
    );

    public static TypeContainer FAIRY_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fairy"),
            ModEffects.FAIRY, false, false, new FairyTrigger()
    );

    public static TypeContainer PSYCHIC_TYPE = registerToggledType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "psychic"),
            ModEffects.PSYCHIC, true, false, new PsychicTrigger()
    );

    public static TypeContainer DARK_TYPE = registerToggledType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "dark"),
            ModEffects.DARK, false, false, new DarkTrigger()
    );

    public static TypeContainer CURSE_TYPE = registerActivatedType(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "curse_type"),
            ModEffects.CURSE_TYPE, false, true, new CurseTypeTrigger()
    );

    public static TypeContainer registerPassiveType(Identifier identifier, MobEffect type, boolean energyBottles, boolean special) {
        TypeContainer typeContainer = new TypeContainer(identifier, type, null, null, energyBottles);

        TYPES.add(typeContainer.type);
        if (!special) ROLLABLE_TYPES.add(typeContainer.type);

        return typeContainer;
    }

    public static TypeContainer registerToggledType(Identifier identifier, MobEffect type, boolean energyBottles, boolean special, ToggledAbility toggled) {
        TypeContainer typeContainer = new TypeContainer(identifier, type, null, toggled, energyBottles);

        TYPES.add(typeContainer.type);
        if (!special) ROLLABLE_TYPES.add(typeContainer.type);

        PressedAbilityKeyC2SHandler.TOGGLED_ABILITIES.put(typeContainer.type, toggled);

        return typeContainer;
    }

    public static TypeContainer registerActivatedType(Identifier identifier, MobEffect type, boolean energyBottles, boolean special, ActivatedAbility activated) {
        TypeContainer typeContainer = new TypeContainer(identifier, type, activated, null, energyBottles);

        TYPES.add(typeContainer.type);
        if (!special) ROLLABLE_TYPES.add(typeContainer.type);

        PressedAbilityKeyC2SHandler.ACTIVATED_ABILITIES.put(typeContainer.type, activated);

        return typeContainer;
    }

    public static void registerTypes() {
        System.out.println("Registering Mod Types for " + LifeLocke.MOD_ID);
    }

    public static class TypeContainer {
        public final Holder<MobEffect> type;
        public final Identifier id;
        public final Optional<ActivatedAbility> activated;
        public final Optional<ToggledAbility> toggled;
        public final Optional<EnergyBottleItem.EnergyBottleGroup> bottles;
        public final @NotNull RKey rKey;

        public TypeContainer(Identifier id, MobEffect type, @Nullable ActivatedAbility activated,
                             @Nullable ToggledAbility toggled, boolean energyBottles) {
            this.id = id;
            this.type = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, type);
            this.activated = Optional.ofNullable(activated);
            this.toggled = Optional.ofNullable(toggled);

            if (activated != null) rKey = RKey.ACTIVE;
            else if (toggled != null) rKey = RKey.TOGGLE;
            else rKey = RKey.ACTIVE;

            if (energyBottles) bottles = Optional.of(new EnergyBottleItem.EnergyBottleGroup(
                    new Item.Properties(), this.type, id));
            else bottles = Optional.empty();
        }

        public enum RKey {
            NOTHING,
            ACTIVE,
            TOGGLE
        }
    }
}
