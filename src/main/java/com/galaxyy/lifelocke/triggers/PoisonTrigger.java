package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class PoisonTrigger implements BlockUseConsumer {
    public void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (!UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())) {
            return;
        }
        if (UpdateData.togglePoisonPower(((iEntityDataSaver) playerEntity))) {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.poison_turned_on"), false);
        } else {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.poison_turned_off"), false);
        }
    }
}
