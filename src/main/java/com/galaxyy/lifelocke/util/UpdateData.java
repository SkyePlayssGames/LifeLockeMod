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

    public static boolean tryAndStoreCooldown(iEntityDataSaver player, long time) {
        NbtCompound nbt = player.getPersistentData();
        long ticks = nbt.getLong("time");
        if (time == ticks) { return false; }

        nbt.putLong("time", time);
        return true;
    }
}
