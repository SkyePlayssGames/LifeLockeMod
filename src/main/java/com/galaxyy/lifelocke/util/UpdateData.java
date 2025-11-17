package com.galaxyy.lifelocke.util;

import net.minecraft.nbt.NbtCompound;

public class UpdateData {
    public static boolean toggleElectricPower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean electricPower = nbt.getBoolean("electric_power").orElse(false);

        electricPower = !electricPower;

        nbt.putBoolean("electric_power", electricPower);
        return electricPower;
    }

    public static boolean toggleIcePower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean icePower = nbt.getBoolean("ice_power").orElse(false);

        icePower = !icePower;

        nbt.putBoolean("ice_power", icePower);
        return icePower;
    }

    public static boolean togglePoisonPower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean poisonPower = nbt.getBoolean("poison_power").orElse(false);

        poisonPower = !poisonPower;

        nbt.putBoolean("poison_power", poisonPower);
        return poisonPower;
    }

    public static boolean toggleRockPower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean rockPower = nbt.getBoolean("rock_power").orElse(false);

        rockPower = !rockPower;

        nbt.putBoolean("rock_power", rockPower);
        return rockPower;
    }

    public static boolean toggleDarkPower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean darkPower = nbt.getBoolean("dark_power").orElse(false);

        darkPower = !darkPower;

        nbt.putBoolean("dark_power", darkPower);
        return darkPower;
    }

    public static boolean tryAndStoreCooldown(iEntityDataSaver player, long time) {
        NbtCompound nbt = player.getPersistentData();
        long ticks = nbt.getLong("time").orElse(0L);
        if (time == ticks) { return false; }

        nbt.putLong("time", time);
        return true;
    }

    public static int[] getTypeList(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getIntArray("types").orElse(new int[] {});
    }

    public static void setTypeList(iEntityDataSaver player, int[] list) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putIntArray("types", list);
    }
}
