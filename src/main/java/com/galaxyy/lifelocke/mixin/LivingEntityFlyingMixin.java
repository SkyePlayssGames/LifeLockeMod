package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.tags.ModTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityFlyingMixin extends Entity {
    @Shadow
    protected abstract void travelFlying(Vec3 vec3, float f);

    @Shadow
    protected abstract float getFlyingSpeed();

    public LivingEntityFlyingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "travel", cancellable = true)
    protected void onTravel(Vec3 vec3, CallbackInfo cir) {
        if (this.getType().is(ModTags.FLYING_MOBS)) {
            this.travelFlying(vec3, this.getFlyingSpeed());
            cir.cancel();
        }
    }
}
