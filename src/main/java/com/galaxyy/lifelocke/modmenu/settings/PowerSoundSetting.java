package com.galaxyy.lifelocke.modmenu.settings;

import com.galaxyy.lifelocke.sound.ModSounds;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.ArrayList;
import java.util.HashMap;

public class PowerSoundSetting extends ModMenuSetting {
    private static final HashMap<String, SoundEvent> stringSoundEventMap = new HashMap<>();
    private static final HashMap<SoundEvent, String> soundEventStringMap = new HashMap<>();
    private static final HashMap<String, SoundEvent> oldStringSoundEventMap = new HashMap<>();
    private static final HashMap<SoundEvent, String> oldSoundEventStringMap = new HashMap<>();
    private static final ArrayList<String> toggledSoundOrder = new ArrayList<>();
    private static final ArrayList<String> activatedSoundOrder = new ArrayList<>();
    public static int amountOfSounds;

    SoundEvent value;

    public PowerSoundSetting(String input) {
        super(input);
        if (input.charAt(3) != 't' && input.charAt(3) != 'a') {
            value = oldStringSoundEventMap.get(input.substring(3));
        } else {
            value = stringSoundEventMap.get(input.substring(3));
        }
    }

    @Override
    public String to_string() {
        return "ps#" + soundEventStringMap.get(value);
    }

    public String legacy_string() {
        return "ps#" + oldSoundEventStringMap.get(value);
    }

    @Override
    public SoundEvent get_powerSound() {
        return value;
    }

    public static PowerSoundSetting cycle(int order, boolean toggled) {
        order += 1;
        if (order >= amountOfSounds) {
            order = 0;
        }
        return new PowerSoundSetting("ps#" + (toggled ? toggledSoundOrder.get(order) : activatedSoundOrder.get(order)));
    }

    private static void registerMapEntry(String string, SoundEvent toggledSoundEvent, SoundEvent activatedSoundEvent, SoundEvent legacy) {
        stringSoundEventMap.put("t"+string, toggledSoundEvent);
        soundEventStringMap.put(toggledSoundEvent, "t"+string);
        stringSoundEventMap.put("a"+string, activatedSoundEvent);
        soundEventStringMap.put(activatedSoundEvent, "a"+string);

        if (legacy != null) {
            oldSoundEventStringMap.put(legacy, string);
            oldStringSoundEventMap.put(string, legacy);
        }

        toggledSoundOrder.add("t"+string);
        activatedSoundOrder.add("a"+string);
    }

    public static void registerSoundEventMaps() {
        registerMapEntry("none", ModSounds.EMPTY, ModSounds.EMPTY, SoundEvents.BLOCK_CANDLE_FALL);
        registerMapEntry("conduit", ModSounds.CONDUIT_TOGGLE, ModSounds.CONDUIT_ACTIVATE, SoundEvents.BLOCK_CONDUIT_DEACTIVATE);
        registerMapEntry("place", ModSounds.PLACE_TOGGLE, ModSounds.PLACE_ACTIVATE, SoundEvents.BLOCK_GRASS_PLACE);
        registerMapEntry("eye", ModSounds.EYE_TOGGLE, ModSounds.EYE_ACTIVATE, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL);
        registerMapEntry("horse", ModSounds.HORSE_TOGGLE, ModSounds.HORSE_ACTIVATE, SoundEvents.ENTITY_HORSE_DEATH);

        amountOfSounds = toggledSoundOrder.size();
    }
}
