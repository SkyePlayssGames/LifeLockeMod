package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class DarkTrigger implements BlockUseConsumer {
    public void accept(PlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (!UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())) {
            return;
        }
        if (UpdateData.toggleDarkPower(((iEntityDataSaver) playerEntity))) {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.power_turned_on",
                    ModEffects.DARK.value().getName()), false);
        } else {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.power_turned_off",
                    ModEffects.DARK.value().getName()), false);
        }
    }
}
