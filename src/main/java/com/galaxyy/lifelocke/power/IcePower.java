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

public class IcePower implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (playerEntity.hasStatusEffect(ModEffects.ICE) && !world.isClient() &&
                (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) &&
                ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().getBoolean("ice_power").orElse(false) &&
                entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100), playerEntity);
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100), playerEntity);
            HungerCost.takeHunger(playerEntity, 1);
        }

        return ActionResult.PASS;
    }
}
