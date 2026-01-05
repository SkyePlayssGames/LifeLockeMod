package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.HungerCost;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
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

public class DarkPower implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (playerEntity.hasStatusEffect(ModEffects.DARK) && !world.isClient() &&
                (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) &&
                ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().getBoolean("dark_power").orElse(false) &&
                entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200), playerEntity);
            HungerCost.takeHunger(playerEntity, 1);
        }

        return ActionResult.PASS;
    }
}
