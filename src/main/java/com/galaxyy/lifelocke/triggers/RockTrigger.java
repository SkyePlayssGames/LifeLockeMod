package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class RockTrigger implements BlockUseConsumer {
    @Override
    public void accept(ServerPlayerEntity playerEntity, World world, Hand hand, Vec3i pos) {
        playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.STUCK, 100, 0, false, false));
    }
}
