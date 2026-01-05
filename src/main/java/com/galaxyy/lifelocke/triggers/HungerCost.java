package com.galaxyy.lifelocke.triggers;

import net.minecraft.entity.player.PlayerEntity;

public class HungerCost {
    public static boolean checkHunger(PlayerEntity playerEntity, int minimum) {
        return (playerEntity.getHungerManager().getFoodLevel() >= minimum);
    }

    public static void takeHunger(PlayerEntity playerEntity, float cost) {
        playerEntity.getHungerManager().addExhaustion(4f * cost);
    }
}
