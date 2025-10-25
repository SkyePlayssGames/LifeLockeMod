package com.galaxyy.lifelocke.util;

import net.minecraft.nbt.NbtCompound;

public class UpdateData {
    public static boolean toggleElectricPower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean electricPower = nbt.getBoolean("electric_power");

        electricPower = !electricPower;

        nbt.putBoolean("electric_power", electricPower);
        return electricPower;
    }

    public static boolean toggleIcePower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean icePower = nbt.getBoolean("ice_power");

        icePower = !icePower;

        nbt.putBoolean("ice_power", icePower);
        return icePower;
    }

    public static boolean togglePoisonPower(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean poisonPower = nbt.getBoolean("poison_power");

        poisonPower = !poisonPower;

        nbt.putBoolean("poison_power", poisonPower);
        return poisonPower;
    }

    public static boolean tryAndStoreCooldown(iEntityDataSaver player, long time) {
        NbtCompound nbt = player.getPersistentData();
        long ticks = nbt.getLong("time");
        if (time == ticks) { return false; }

        nbt.putLong("time", time);
        return true;
    }
}
