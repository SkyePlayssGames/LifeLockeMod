package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityCanFreezeMixin extends Entity implements Attackable, WaypointTransmitter {
    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    public LivingEntityCanFreezeMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "canFreeze", cancellable = true)
    protected void onCanFreeze(CallbackInfoReturnable<Boolean> cir) {
        if (this.hasEffect(ModEffects.ICE)) {
            cir.setReturnValue(false);
        }
    }
}
