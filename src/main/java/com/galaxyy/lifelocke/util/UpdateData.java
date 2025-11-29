package com.galaxyy.lifelocke.util;

import com.galaxyy.lifelocke.networking.RenderTypeIconS2CPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class UpdateData {
    public static void setShownTypeIcon(ServerPlayerEntity playerEntity, RenderTypeIconS2CPayload.ICONS icon) {
        ((iEntityDataSaver) playerEntity).getPersistentData().putInt("type_icon", icon.ordinal());
        ServerPlayNetworking.send(playerEntity,
                new RenderTypeIconS2CPayload(icon.ordinal())
        );
    }

    public static boolean getAndSetRolltypeConfirmation(iEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        boolean confirmation = nbt.getBoolean("rolltype_confirmation").orElse(false);
        nbt.putBoolean("rolltype_confirmation", true);

        return confirmation;
    }

    public static boolean toggleElectricPower(ServerPlayerEntity playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        NbtCompound nbt = player.getPersistentData();
        boolean electricPower = nbt.getBoolean("electric_power").orElse(false);

        electricPower = !electricPower;

        nbt.putBoolean("electric_power", electricPower);

        if (electricPower) {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.ELECTRIC);
        } else {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.NONE);
        }

        return electricPower;
    }

    public static boolean toggleIcePower(ServerPlayerEntity playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        NbtCompound nbt = player.getPersistentData();
        boolean icePower = nbt.getBoolean("ice_power").orElse(false);

        icePower = !icePower;

        if (icePower) {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.ICE);
        } else {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.NONE);
        }

        nbt.putBoolean("ice_power", icePower);
        return icePower;
    }

    public static boolean togglePoisonPower(ServerPlayerEntity playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        NbtCompound nbt = player.getPersistentData();
        boolean poisonPower = nbt.getBoolean("poison_power").orElse(false);

        poisonPower = !poisonPower;

        if (poisonPower) {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.POISON);
        } else {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.NONE);
        }

        nbt.putBoolean("poison_power", poisonPower);
        return poisonPower;
    }

    public static boolean toggleDarkPower(ServerPlayerEntity playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        NbtCompound nbt = player.getPersistentData();
        boolean darkPower = nbt.getBoolean("dark_power").orElse(false);

        darkPower = !darkPower;

        if (darkPower) {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.DARK);
        } else {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.NONE);
        }

        nbt.putBoolean("dark_power", darkPower);
        return darkPower;
    }

    public static boolean togglePsychicPower(ServerPlayerEntity playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        NbtCompound nbt = player.getPersistentData();
        boolean psychicPower = nbt.getBoolean("psychic_power").orElse(false);

        psychicPower = !psychicPower;

        if (psychicPower) {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.PSYCHIC);
        } else {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.NONE);
        }

        nbt.putBoolean("psychic_power", psychicPower);
        return psychicPower;
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
