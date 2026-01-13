package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class NormalPower implements ServerLivingEntityEvents.AllowDeath {
    @Override
    public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        if (!(entity instanceof ServerPlayer player)) {
            return true;
        }
        ItemStack pocketItem = UpdateData.swapNormalItemStack(player, ItemStack.EMPTY);

        if (!pocketItem.isEmpty()) {
            player.getInventory().placeItemBackInInventory(pocketItem);
        }

        return true;
    }
}
