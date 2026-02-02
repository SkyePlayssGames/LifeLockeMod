package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.tags.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
    protected abstract void travelFlying(Vec3 vec3, float f, float g, float h);

    @Shadow
    public abstract double getAttributeValue(Holder<Attribute> holder);

    public LivingEntityFlyingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "travel", cancellable = true)
    protected void onTravel(Vec3 vec3, CallbackInfo cir) {
        if (this.getType().is(ModTags.FLYING_MOBS)) {
            float flySpeed = ((float) this.getAttributeValue(Attributes.FLYING_SPEED));
            this.travelFlying(vec3, flySpeed, flySpeed, flySpeed);
            cir.cancel();
        }
    }
}
