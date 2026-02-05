package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class RemoveIllegalItemsEvent implements ServerTickEvents.EndWorldTick{
    @Override
    public void onEndTick(ServerLevel serverWorld) {
        for (ServerPlayer player : serverWorld.players()) {
            Inventory inventory = player.getInventory();
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (item.getOrDefault(ModDataComponents.STEEL, null) != null &&
                        !player.hasEffect(Types.STEEL_TYPE.type)) {
                    inventory.removeItemNoUpdate(i);
                }
                if (item.getOrDefault(ModDataComponents.FLYING, null) != null &&
                        !player.hasEffect(Types.FLYING_TYPE.type)) {
                    inventory.removeItemNoUpdate(i);
                }
            }
        }
    }
}
