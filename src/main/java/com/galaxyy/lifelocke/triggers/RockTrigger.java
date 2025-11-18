package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RockTrigger implements BlockUseConsumer {
    public void accept(PlayerEntity playerEntity, World world, Hand hand, BlockPos blockPos) {
        if (!UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())) {
            return;
        }
        if (UpdateData.toggleRockPower(((iEntityDataSaver) playerEntity))) {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.rock_turned_on"), false);
        } else {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.rock_turned_off"), false);
        }
    }
}
