package com.galaxyy.lifelocke.sound;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent EMPTY = registerSound("empty");
    public static final SoundEvent CONDUIT_TOGGLE = registerSound("toggle.conduit");
    public static final SoundEvent CONDUIT_ACTIVATE = registerSound("activate.conduit");
    public static final SoundEvent PLACE_TOGGLE = registerSound("toggle.place");
    public static final SoundEvent PLACE_ACTIVATE = registerSound("activate.place");
    public static final SoundEvent EYE_TOGGLE = registerSound("toggle.eye");
    public static final SoundEvent EYE_ACTIVATE = registerSound("activate.eye");
    public static final SoundEvent HORSE_TOGGLE = registerSound("toggle.horse");
    public static final SoundEvent HORSE_ACTIVATE = registerSound("activate.horse");


    private static SoundEvent registerSound(String name) {
        Identifier identifier = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void registerSounds() {
        System.out.println("Registering Mod Sounds for " + LifeLocke.MOD_ID);
    }
}
