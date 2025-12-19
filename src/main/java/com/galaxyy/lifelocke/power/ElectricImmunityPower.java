package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import org.jspecify.annotations.NonNull;

public class ElectricImmunityPower implements ServerLivingEntityEvents.AllowDamage{
    @Override
    public boolean allowDamage(LivingEntity entity, @NonNull DamageSource source, float amount) {
        DamageType lightningDamageType = entity.getRegistryManager().getEntryOrThrow(DamageTypes.LIGHTNING_BOLT).value();
        if (entity.hasStatusEffect(ModEffects.ELECTRIC) && source.getType() == lightningDamageType) {
            return false;
        }
        return true;
    }
}
