package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.triggers.HungerCost;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class ElectricPower extends ToggledPower implements AttackEntityCallback {
    @Override
    protected Types.TypeContainer getType() {
        return Types.ELECTRIC_TYPE;
    }

    @Override
    public InteractionResult interact(Player playerEntity, Level world, InteractionHand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (canAffect(playerEntity, world, entity)) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) world, entity.blockPosition(), EntitySpawnReason.TRIGGERED);
            HungerCost.takeHunger(playerEntity, 2);
        }

        return InteractionResult.PASS;
    }
}
