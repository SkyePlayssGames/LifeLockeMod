package com.galaxyy.lifelocke.sound;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

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
        Identifier identifier = Identifier.of(LifeLocke.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void registerSounds() {
        System.out.println("Registering Mod Sounds for " + LifeLocke.MOD_ID);
    }
}
