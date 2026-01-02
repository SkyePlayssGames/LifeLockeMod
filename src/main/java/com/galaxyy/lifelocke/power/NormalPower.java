package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.util.UpdateData;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class NormalPower implements ServerLivingEntityEvents.AllowDeath {
    @Override
    public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        if (!(entity instanceof ServerPlayerEntity player)) {
            return true;
        }
        ItemStack pocketItem = UpdateData.swapNormalItemStack(player, ItemStack.EMPTY);
        player.getInventory().offerOrDrop(pocketItem);

        return true;
    }
}
