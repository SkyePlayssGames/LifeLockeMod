package com.galaxyy.lifelocke.modmenu;

import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.IntSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import com.terraformersmc.modmenu.util.mod.Mod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SettingsFileHandler {
    public enum SETTINGS {
        VERSION,
        NULL_ICON,
        POWER_DEFAULT
    }

    private static final ModMenuSetting[] DEFAULT = {
            new IntSetting("1"),
            new BooleanSetting("F"),
            new BooleanSetting("F")
    };

    private static final int LINE_AMOUNT = DEFAULT.length;

    private static final File settings = new File(System.getenv("APPDATA")+"\\.minecraft\\.lifelocke");

    public static void create() {
        try {
            if (settings.createNewFile()) {
                write(DEFAULT);
            }
        } catch (IOException e) {
            System.out.println("ERROR IN FILE CREATING!!");
            System.err.println(e);
        }
    }

    public static void write(ModMenuSetting[] input) {
        try (FileWriter writer = new FileWriter(settings)) {
            for (ModMenuSetting i : input) {
                writer.write(i.to_string());
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("ERROR IN FILE WRITING!!");
            System.out.println(e.getStackTrace());
        }
    }

    public static ModMenuSetting[] read() {
        try (Scanner scanner = new Scanner(settings)) {
            ModMenuSetting[] result = new ModMenuSetting[LINE_AMOUNT];
            for (int i = 0; i < LINE_AMOUNT; i++) {
                result[i] = getModMenuSetting(scanner.nextLine());
            }
            return result;
        } catch (IOException e) {
            System.out.println("ERROR IN FILE READING!!");
            System.out.println(e.getStackTrace());
            return new ModMenuSetting[] {};
        }
    }

    private static ModMenuSetting getModMenuSetting(String input) {
        System.out.println(input);
        System.out.println(input.matches("/\\d+/"));
        if (Objects.equals(input, "T") || Objects.equals(input, "F")) {
            return new BooleanSetting(input);
        }
        try {
            return new IntSetting(input);
        } catch (NumberFormatException ignored) {}
        return null;
    }
}
