package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowMixin extends Block implements FluidDrainable {
    public PowderSnowMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "canWalkOnPowderSnow", cancellable = true)
    private static void onCanWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(ModEffects.ICE)) {
            cir.setReturnValue(true);
        }
    }
}
