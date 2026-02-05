package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public class ElectricImmunityPower implements ServerLivingEntityEvents.AllowDamage{
    @Override
    public boolean allowDamage(LivingEntity entity, @NonNull DamageSource source, float amount) {
        DamageType lightningDamageType = entity.registryAccess().getOrThrow(DamageTypes.LIGHTNING_BOLT).value();
        if (entity.hasEffect(Types.ELECTRIC_TYPE.type) && source.type() == lightningDamageType) {
            return false;
        }
        return true;
    }
}
