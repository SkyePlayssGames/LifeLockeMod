package com.galaxyy.lifelocke.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.List;

public class AddTooltipsEvent implements ItemTooltipCallback {
    private static final HashMap<Item, Text> tooltips = new HashMap<>();

    @Override
    public void getTooltip(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipType tooltipType, List<Text> list) {
        if (!tooltips.containsKey(itemStack.getItem())) {
            return;
        }
        list.add(tooltips.get(itemStack.getItem()));
    }

    public static void append_tooltip(Item item, Text tooltip) {
        tooltips.put(item, tooltip);
    }
}
