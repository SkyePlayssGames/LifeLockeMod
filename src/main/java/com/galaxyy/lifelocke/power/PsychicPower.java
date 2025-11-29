package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PsychicPower implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (playerEntity.hasStatusEffect(ModEffects.PSYCHIC) && !world.isClient() &&
                (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) &&
                ((iEntityDataSaver) playerEntity).getPersistentData().getBoolean("psychic_power").orElse(false) &&
                entity.isAlive()) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40), playerEntity);
            HungerCost.takeHunger(playerEntity, 1);
        }

        return ActionResult.PASS;
    }
}
