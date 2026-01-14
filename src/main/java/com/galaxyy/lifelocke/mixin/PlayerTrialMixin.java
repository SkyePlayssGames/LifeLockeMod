package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerTrialMixin extends Avatar implements ContainerUser {
    protected PlayerTrialMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "getDestroySpeed", cancellable = true)
    private void getDestroySpeed(CallbackInfoReturnable<Float> cir) {
        if (this.hasEffect(ModEffects.IN_TRIAL)) {
            cir.setReturnValue(0f);
        }
    }
}
