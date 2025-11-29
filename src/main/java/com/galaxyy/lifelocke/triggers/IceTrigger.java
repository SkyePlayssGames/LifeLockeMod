package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class IceTrigger implements BlockUseConsumer {
    public void accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        if (!UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())) {
            return;
        }
        if (UpdateData.toggleIcePower(playerEntity)) {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.power_turned_on",
                    ModEffects.ICE.value().getName()), false);
        } else {
            playerEntity.sendMessage(Text.translatable("text.lifelocke.power_turned_off",
                    ModEffects.ICE.value().getName()), false);
        }
    }
}
