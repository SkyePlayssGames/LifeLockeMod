package com.galaxyy.lifelocke.gamerule;

import com.galaxyy.lifelocke.LifeLocke;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGameRules {
    public static final GameRules.Key<GameRules.BooleanRule> FIGHTING_CRAFTING_NERF =
            GameRuleRegistry.register("fightingCraftingNerf", GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(true)
            );

    public static final GameRules.Key<GameRules.BooleanRule> TYPE_DUPLICATION =
            GameRuleRegistry.register("typeDuplication", GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(false)
            );

    public static final GameRules.Key<GameRules.BooleanRule> SPECIAL_TYPE_ROLL =
            GameRuleRegistry.register("specialTypeRoll", GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(false)
            );

    public static void registerGameRules() {
        System.out.println("Registering Game Rules for " + LifeLocke.MOD_ID);
    }
}
