package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.triggers.HungerCost;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class PoisonPower extends ToggledPower implements AttackEntityCallback {
    @Override
    protected Types.TypeContainer getType() {
        return Types.POISON_TYPE;
    }

    @Override
    public InteractionResult interact(Player playerEntity, Level world, InteractionHand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (canAffect(playerEntity, world, entity)) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, 100), playerEntity);
            HungerCost.takeHunger(playerEntity, 1);
        }

        return InteractionResult.PASS;
    }
}
