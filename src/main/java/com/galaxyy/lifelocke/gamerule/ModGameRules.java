package com.galaxyy.lifelocke.gamerule;

import com.galaxyy.lifelocke.LifeLocke;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;

public class ModGameRules {

    public static final GameRule<Boolean> FIGHTING_CRAFTING_NERF =
            GameRuleBuilder.forBoolean(true).buildAndRegister(
                    Identifier.of(LifeLocke.MOD_ID, "fighting_crafting_nerf")
            );

    public static final GameRule<Boolean> TYPE_DUPLICATION =
            GameRuleBuilder.forBoolean(false).buildAndRegister(
                    Identifier.of(LifeLocke.MOD_ID, "type_duplication")
            );

    public static final GameRule<Boolean> SPECIAL_TYPE_ROLL =
            GameRuleBuilder.forBoolean(false).buildAndRegister(
                    Identifier.of(LifeLocke.MOD_ID, "special_type_roll")
            );

    public static void registerGameRules() {
        System.out.println("Registering Game Rules for " + LifeLocke.MOD_ID);
    }
}
