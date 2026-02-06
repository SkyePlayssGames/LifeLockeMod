package com.galaxyy.lifelocke.playerdata;

import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.networking.RenderTypeIconS2CPayload;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
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

    public static void setShownTypeIcon(ServerPlayer playerEntity, Identifier id) {
        ServerPlayNetworking.send(playerEntity,
                new RenderTypeIconS2CPayload(id)
        );
    }

    public static boolean getAndSetRolltypeConfirmation(iEntityDataSaver player) {
        CompoundTag nbt = player.lifelocke$getPersistentData();
        boolean confirmation = nbt.getBoolean("rolltype_confirmation").orElse(false);
        nbt.putBoolean("rolltype_confirmation", true);

        return confirmation;
    }

    public static void setToggledPower(ServerPlayer playerEntity, Types.TypeContainer type, boolean showText) {
        CompoundTag nbt = ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData();
        String power = nbt.getStringOr("toggled_power", "lifelocke:null");

        if (power.equals(type.id.toString())) {
            nbt.putString("toggled_power", "lifelocke:null");
            if (showText) {
                playerEntity.displayClientMessage(Component.translatable("text.lifelocke.power_turned_off",
                        type.type.value().getDisplayName()), false);
            }
        } else {
            nbt.putString("toggled_power", type.id.toString());
            if (showText) {
                playerEntity.displayClientMessage(Component.translatable("text.lifelocke.power_turned_on",
                        type.type.value().getDisplayName()), false);
            }
        }

        setShownTypeIcon(playerEntity, Identifier.parse(nbt.getStringOr("toggled_power", "lifelocke:null")));
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
