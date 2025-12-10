package com.galaxyy.lifelocke.modmenu.settings;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Arrays;
import java.util.HashMap;

public class PowerSoundSetting extends ModMenuSetting {
    private static final HashMap<String, SoundEvent> stringSoundEventMap = new HashMap<>();
    private static final HashMap<SoundEvent, String> soundEventStringMap = new HashMap<>();
    private static String[] soundOrder = new String[] {};
    public static int amountOfSounds;

    SoundEvent value;

    public PowerSoundSetting(String input) {
        super(input);
        value = stringSoundEventMap.get(input.substring(3));
    }

    @Override
    public String to_string() {
        return "ps#" + soundEventStringMap.get(value);
    }

    @Override
    public SoundEvent get_powerSound() {
        return value;
    }

    public static PowerSoundSetting cycle(int order) {
        order += 1;
        if (order >= soundOrder.length) {
            order = 0;
        }
        return new PowerSoundSetting("ps#" + soundOrder[order]);
    }

    private static void registerMapEntry(String string, SoundEvent soundEvent) {
        stringSoundEventMap.put(string, soundEvent);
        soundEventStringMap.put(soundEvent, string);

        String[] new_order = new String[soundOrder.length + 1];
        int i = 0;
        while (i < soundOrder.length) {
            new_order[i] = soundOrder[i];
            i++;
        }
        new_order[soundOrder.length] = string;
        soundOrder = new_order;
    }

    public static void registerSoundEventMaps() {
        registerMapEntry("none", SoundEvents.BLOCK_CANDLE_FALL);
        registerMapEntry("conduit", SoundEvents.BLOCK_CONDUIT_DEACTIVATE);
        registerMapEntry("place", SoundEvents.BLOCK_GRASS_PLACE);
        registerMapEntry("eye", SoundEvents.BLOCK_END_PORTAL_FRAME_FILL);
        registerMapEntry("horse", SoundEvents.ENTITY_HORSE_DEATH);

        amountOfSounds = soundOrder.length;
    }
}
