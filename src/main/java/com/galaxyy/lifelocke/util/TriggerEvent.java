package com.galaxyy.lifelocke.util;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.util.Map;

public class TriggerEvent implements UseBlockCallback {
    private static final Map<RegistryEntry<StatusEffect>, BlockUseConsumer> EFFECT_FUNCTION_MAP = Map.of(
            ModEffects.ELECTRIC, new ElectricTrigger()
    );

    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient() || !playerEntity.isSneaking() || !playerEntity.getMainHandStack().isEmpty()) {
            return ActionResult.PASS;
        }

        for (RegistryEntry<StatusEffect> effect: EFFECT_FUNCTION_MAP.keySet()) {
            if (playerEntity.hasStatusEffect(effect)) {
                EFFECT_FUNCTION_MAP.get(effect).accept(playerEntity, world, hand, blockHitResult);
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
