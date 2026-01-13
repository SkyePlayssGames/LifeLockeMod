package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

public class IceEffect extends MobEffect {
    protected IceEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        SettingsFileHandler.create();
        Boolean setting = SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal()].get_boolean();
        if (UpdateData.toggleIcePower((ServerPlayer) entity) != setting) {
            UpdateData.toggleIcePower(((ServerPlayer) entity));
        }
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player)) {
            return true;
        }
        iEntityDataSaver playerData = (iEntityDataSaver) entity;
        if (!(playerData.lifelocke$getPersistentData().getBoolean("ice_power")).orElse(false)) {
            return true;
        }
        Player player = ((Player) playerData);
        BlockPos start = player.blockPosition().below();

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
            if (world.getBlockState(blockPos).is(Blocks.WATER)) {
                world.setBlockAndUpdate(blockPos, Blocks.FROSTED_ICE.defaultBlockState());
            } else if (world.getBlockState(blockPos).is(Blocks.LAVA)) {
                world.setBlockAndUpdate(blockPos, ModBlocks.FROSTED_OBSIDIAN.defaultBlockState());
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
