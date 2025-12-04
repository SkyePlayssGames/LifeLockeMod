package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class IceEffect extends StatusEffect {
    protected IceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        SettingsFileHandler.create();
        Boolean setting = SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()].get_boolean();
        if (UpdateData.toggleIcePower((ServerPlayerEntity) entity) != setting) {
            UpdateData.toggleIcePower(((ServerPlayerEntity) entity));
        }
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof PlayerEntity)) {
            return true;
        }
        iEntityDataSaver playerData = (iEntityDataSaver) entity;
        if (!(playerData.lifelocke$getPersistentData().getBoolean("ice_power")).orElse(false)) {
            return true;
        }
        PlayerEntity player = ((PlayerEntity) playerData);
        BlockPos start = player.getBlockPos().down();

        BlockPos[] toReplace = new BlockPos[25];
        BlockPos[] eastWestToDo = new BlockPos[5];
        eastWestToDo[0] = start;

        for (int i=1; i<=2; i++) {
            eastWestToDo[i*2-1] = start.north(i);
            eastWestToDo[i*2] = start.south(i);
        }

        int i = 0;
        for (BlockPos blockPos : eastWestToDo) {
            toReplace[i] = blockPos.east(2);
            i++;
            toReplace[i] = blockPos.east(1);
            i++;
            toReplace[i] = blockPos;
            i++;
            toReplace[i] = blockPos.west(1);
            i++;
            toReplace[i] = blockPos.west(2);
            i++;
        }

        for (BlockPos blockPos : toReplace) {
            if (world.getBlockState(blockPos).isOf(Blocks.WATER)) {
                world.setBlockState(blockPos, Blocks.FROSTED_ICE.getDefaultState());
            } else if (world.getBlockState(blockPos).isOf(Blocks.LAVA)) {
                world.setBlockState(blockPos, Blocks.FROSTED_ICE.getDefaultState());
            }
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
