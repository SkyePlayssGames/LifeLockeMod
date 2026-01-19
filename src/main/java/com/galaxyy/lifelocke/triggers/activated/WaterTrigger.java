package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class WaterTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        Level level = playerEntity.level();
        if (level.environmentAttributes().getValue(
                EnvironmentAttributes.WATER_EVAPORATES, BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()))) {
            level.playSound(
                    null, BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()),
                    SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F,
                    2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F
            );
            return false;
        }
        playerEntity.level().setBlockAndUpdate(playerEntity.blockPosition(), Blocks.WATER.defaultBlockState());
        HungerCost.takeHunger(playerEntity, 1);
        return true;
    }
}
