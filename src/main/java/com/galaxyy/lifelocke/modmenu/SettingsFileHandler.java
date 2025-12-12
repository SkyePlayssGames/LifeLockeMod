package com.galaxyy.lifelocke.modmenu;

import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.IntSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import com.galaxyy.lifelocke.modmenu.settings.PowerSoundSetting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class SettingsFileHandler {
    public enum SETTINGS {
        VERSION,
        SHOW_TYPE_ICON,
        POWER_DEFAULT,
        POWER_SOUND_TOGGLE,
        POWER_SOUND_TOGGLE_ORDER,
        POWER_SOUND_ACTIVE,
        POWER_SOUND_ACTIVE_ORDER
    }

    private static final HashMap<Integer, Integer> LINES_PER_VERSION = new HashMap<>();

    private static final ModMenuSetting[] DEFAULT = {
            new IntSetting("3"),
            new BooleanSetting("T"),
            new BooleanSetting("F"),
            new PowerSoundSetting("ps#conduit"),
            new IntSetting("1"),
            new PowerSoundSetting("ps#conduit"),
            new IntSetting("1")
    };

    private static final int LINE_AMOUNT = DEFAULT.length;

    private static final File settings = new File(System.getenv("APPDATA")+"\\.minecraft\\.lifelocke");

    private static Boolean file_locked = false;

    public static void create() {
        try {
            if (settings.createNewFile()) {
                try_write(DEFAULT);
            } else {
                try_update();
            }
        } catch (IOException e) {
            System.out.println("ERROR IN FILE CREATING!!");
            System.err.println(e);
        }
    }

    private static void try_update() {
        Integer version = try_read(1)[SETTINGS.VERSION.ordinal()].get_int();
        ModMenuSetting[] settings_preupdate = try_read(LINES_PER_VERSION.get(version));
        ModMenuSetting[] settings_postupdate = DEFAULT.clone();
        System.arraycopy(settings_preupdate, 0, settings_postupdate, 0, settings_preupdate.length);

        if (version < 3) {
            System.out.println(((PowerSoundSetting) settings_postupdate[SETTINGS.POWER_SOUND_TOGGLE.ordinal()]).legacy_string());
            settings_postupdate[SETTINGS.POWER_SOUND_TOGGLE.ordinal()] = new PowerSoundSetting("ps#t" + ((PowerSoundSetting) settings_postupdate[SETTINGS.POWER_SOUND_TOGGLE.ordinal()]).legacy_string().substring(3));
            settings_postupdate[SETTINGS.POWER_SOUND_ACTIVE.ordinal()] = new PowerSoundSetting("ps#a" + ((PowerSoundSetting) settings_postupdate[SETTINGS.POWER_SOUND_ACTIVE.ordinal()]).legacy_string().substring(3));
        }

        settings_postupdate[SETTINGS.VERSION.ordinal()] = DEFAULT[SETTINGS.VERSION.ordinal()];
        try_write(settings_postupdate);
    }

    public static void try_write(ModMenuSetting[] input) {
        while (!write(input)) {}
    }

    public static ModMenuSetting[] try_read(Integer lines) {
        ModMenuSetting[] result = null;
        while (result == null) {
            result = read(lines);
        }
        return result;
    }


    private static boolean write(ModMenuSetting[] input) {
        if (file_locked) {
            return false;
        }
        file_locked = true;
        try (FileWriter writer = new FileWriter(settings)) {
            for (ModMenuSetting i : input) {
                writer.write(i.to_string());
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("ERROR IN FILE WRITING!!");
            System.out.println(e.getStackTrace());
        }
        file_locked = false;
        return true;
    }

    private static ModMenuSetting[] read(Integer lines) {
        if (file_locked) {
            return null;
        }
        file_locked = true;
        lines = lines == null ? LINE_AMOUNT : lines;
        try (Scanner scanner = new Scanner(settings)) {
            ModMenuSetting[] result = new ModMenuSetting[lines];
            for (int i = 0; i < lines; i++) {
                result[i] = getModMenuSetting(scanner.nextLine());
            }
            file_locked = false;
            return result;
        } catch (IOException e) {
            System.out.println("ERROR IN FILE READING!!");
            System.out.println(e.getStackTrace());
            file_locked = false;
            return new ModMenuSetting[] {};
        }
    }

    private static ModMenuSetting getModMenuSetting(String input) {
        if (Objects.equals(input, "T") || Objects.equals(input, "F")) {
            return new BooleanSetting(input);
        }
        try {
            return new IntSetting(input);
        } catch (NumberFormatException ignored) {}
        if (input.startsWith("ps#")) {
            return new PowerSoundSetting(input);
        }
        return null;
    }

    public static void registerSettingsFile() {
        LINES_PER_VERSION.put(1, 3);
        LINES_PER_VERSION.put(2, 7);
        LINES_PER_VERSION.put(3, 7);
    }
}
