package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.waypoint.ServerWaypoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityCanFreezeMixin extends Entity implements Attackable, ServerWaypoint {
    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    public LivingEntityCanFreezeMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "canFreeze", cancellable = true)
    protected void onCanFreeze(CallbackInfoReturnable<Boolean> cir) {
        if (this.hasStatusEffect(ModEffects.ICE)) {
            cir.setReturnValue(false);
        }
    }
}
