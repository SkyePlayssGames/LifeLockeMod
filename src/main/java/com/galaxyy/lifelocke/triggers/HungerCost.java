package com.galaxyy.lifelocke.triggers;

import net.minecraft.world.entity.player.Player;

public class HungerCost {
    public static boolean checkHunger(Player playerEntity, int minimum) {
        return (playerEntity.getFoodData().getFoodLevel() >= minimum);
    }

    public static void takeHunger(Player playerEntity, float cost) {
        playerEntity.getFoodData().addExhaustion(4f * cost);
    }
}
