package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GrassPower implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (playerEntity.hasStatusEffect(ModEffects.GRASS) && !world.isClient() && entity instanceof LivingEntity) {
            playerEntity.heal(1);
        }

        return ActionResult.PASS;
    }
}
