package com.galaxyy.lifelocke.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.HashMap;
import java.util.List;

public class AddTooltipsEvent implements ItemTooltipCallback {
    private static final HashMap<Item, Component> tooltips = new HashMap<>();

    @Override
    public void getTooltip(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipFlag tooltipType, List<Component> list) {
        if (!tooltips.containsKey(itemStack.getItem())) {
            return;
        }
        list.add(tooltips.get(itemStack.getItem()));
    }

    public static void append_tooltip(Item item, Component tooltip) {
        tooltips.put(item, tooltip);
    }
}
