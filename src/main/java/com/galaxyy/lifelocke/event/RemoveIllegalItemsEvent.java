package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class RemoveIllegalItemsEvent implements ServerTickEvents.EndWorldTick{
    @Override
    public void onEndTick(ServerWorld serverWorld) {
        for (ServerPlayerEntity player : serverWorld.getPlayers()) {
            PlayerInventory inventory = player.getInventory();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack item = inventory.getStack(i);
                if (item.getOrDefault(ModDataComponents.STEEL, null) != null &&
                        !player.hasStatusEffect(ModEffects.STEEL)) {
                    inventory.removeStack(i);
                }
                if (item.getOrDefault(ModDataComponents.FLYING, null) != null &&
                        !player.hasStatusEffect(ModEffects.FLYING)) {
                    inventory.removeStack(i);
                }
            }
        }
    }
}
