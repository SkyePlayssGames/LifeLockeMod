package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class ElectricTrigger implements BlockUseConsumer {
    public void accept(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        if (!UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())) {
            return;
        }
        if (UpdateData.toggleElectricPower(((iEntityDataSaver) playerEntity))) {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.electric_turned_on"));
        } else {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.electric_turned_off"));
        }
    }
}
