package com.galaxyy.lifelocke.playerdata;

import com.galaxyy.lifelocke.networking.RenderTypeIconS2CPayload;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class UpdateData {
    public static ItemStack swapNormalItemStack(ServerPlayer playerEntity, ItemStack itemStack) {
        CompoundTag nbt = ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData();
        Tag newItem = ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, itemStack).result().orElse(new CompoundTag());
        CompoundTag oldItemNbt = nbt.getCompound("normal_item").orElse(null);
        ItemStack oldItem = ItemStack.EMPTY;
        if (oldItemNbt != null) {
            oldItem = ItemStack.CODEC.decode(NbtOps.INSTANCE, oldItemNbt).result()
                    .orElse(Pair.of(ItemStack.EMPTY, new CompoundTag())).getFirst();
        }
        nbt.put("normal_item", newItem);
        return oldItem;
    }

    public static void setStuckBlockPos(ServerPlayer playerEntity, BlockPos pos) {
        CompoundTag nbt = ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData();
        nbt.putIntArray("stuck_block_pos", new int[] {pos.getX(), pos.getY(), pos.getZ()});
    }

    public static BlockPos getStuckBlockPos(ServerPlayer playerEntity) {
        CompoundTag nbt = ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData();
        int[] ints = nbt.getIntArray("stuck_block_pos").orElse(new int[] {0,0,0});
        return new BlockPos(ints[0], ints[1], ints[2]);
    }

    public static int getTimeSneaked(ServerPlayer playerEntity) {
        return ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().getIntOr("time_sneaked", 0);
    }

    public static int incrementTimeSneaked(ServerPlayer playerEntity) {
        CompoundTag nbt = ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData();
        int time = getTimeSneaked(playerEntity);
        nbt.putInt("time_sneaked", time+1);

        return time + 1;
    }

    public static void resetTimeSneaked(ServerPlayer playerEntity) {
        ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().putInt("time_sneaked", 0);
    }

    public static void setShownTypeIcon(ServerPlayer playerEntity, RenderTypeIconS2CPayload.ICONS icon) {
        ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().putInt("type_icon", icon.ordinal());
        ServerPlayNetworking.send(playerEntity,
                new RenderTypeIconS2CPayload(icon.ordinal())
        );
    }

    public static boolean getAndSetRolltypeConfirmation(iEntityDataSaver player) {
        CompoundTag nbt = player.lifelocke$getPersistentData();
        boolean confirmation = nbt.getBoolean("rolltype_confirmation").orElse(false);
        nbt.putBoolean("rolltype_confirmation", true);

        return confirmation;
    }

    public static boolean toggleElectricPower(ServerPlayer playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        CompoundTag nbt = player.lifelocke$getPersistentData();
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

    public static boolean toggleGhostPower(ServerPlayer playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        CompoundTag nbt = player.lifelocke$getPersistentData();
        boolean ghostPower = nbt.getBoolean("ghost_power").orElse(false);

        ghostPower = !ghostPower;

        nbt.putBoolean("ghost_power", ghostPower);

        if (ghostPower) {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.GHOST);
        } else {
            setShownTypeIcon(playerEntity, RenderTypeIconS2CPayload.ICONS.NONE);
        }

        return ghostPower;
    }

    public static boolean toggleIcePower(ServerPlayer playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        CompoundTag nbt = player.lifelocke$getPersistentData();
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

    public static boolean togglePoisonPower(ServerPlayer playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        CompoundTag nbt = player.lifelocke$getPersistentData();
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

    public static boolean toggleDarkPower(ServerPlayer playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        CompoundTag nbt = player.lifelocke$getPersistentData();
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

    public static boolean togglePsychicPower(ServerPlayer playerEntity) {
        iEntityDataSaver player = ((iEntityDataSaver) playerEntity);
        CompoundTag nbt = player.lifelocke$getPersistentData();
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
        CompoundTag nbt = player.lifelocke$getPersistentData();
        long ticks = nbt.getLong("time").orElse(0L);
        if (time == ticks) { return false; }

        nbt.putLong("time", time);
        return true;
    }

    public static int[] getTypeList(iEntityDataSaver player) {
        CompoundTag nbt = player.lifelocke$getPersistentData();
        return nbt.getIntArray("types").orElse(new int[] {});
    }

    public static void setTypeList(iEntityDataSaver player, int[] list) {
        CompoundTag nbt = player.lifelocke$getPersistentData();
        nbt.putIntArray("types", list);
    }
}
