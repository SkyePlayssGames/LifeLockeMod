package com.galaxyy.lifelocke.util;

import net.minecraft.entity.player.PlayerEntity;

public class HungerCost {
    public static boolean check_hunger(PlayerEntity playerEntity, int minimum) {
        return (playerEntity.getHungerManager().getFoodLevel() >= minimum);
    }

    public static void take_hunger(PlayerEntity playerEntity, int cost) {
        playerEntity.getHungerManager().addExhaustion(4f * cost);
    }
}
