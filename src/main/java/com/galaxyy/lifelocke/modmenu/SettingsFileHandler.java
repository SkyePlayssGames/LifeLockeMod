package com.galaxyy.lifelocke.modmenu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SettingsFileHandler {
    private static final String[] DEFAULT = {
            "0"
    };
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

    public static void write(String[] input) {
        try (FileWriter writer = new FileWriter(settings)) {
            for (String i : input) {
                writer.write(i);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("ERROR IN FILE WRITING!!");
            System.out.println(e.getStackTrace());
        }
    }

    public static String[] read() {
        try (Scanner scanner = new Scanner(settings)) {
            String[] result = new String[1];
            result[0] = scanner.nextLine();
            return result;
        } catch (IOException e) {
            System.out.println("ERROR IN FILE READING!!");
            System.out.println(e.getStackTrace());
            return new String[] {};
        }
    }
}
