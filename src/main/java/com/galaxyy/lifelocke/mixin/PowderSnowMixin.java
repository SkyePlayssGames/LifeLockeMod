package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowMixin extends Block implements BucketPickup {
    public PowderSnowMixin(Properties settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "canEntityWalkOnPowderSnow", cancellable = true)
    private static void onCanWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity && ((LivingEntity) entity).hasEffect(ModEffects.ICE)) {
            cir.setReturnValue(true);
        }
    }
}
