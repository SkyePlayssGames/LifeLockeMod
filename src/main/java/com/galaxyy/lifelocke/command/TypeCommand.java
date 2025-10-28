package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TypeCommand implements net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback {
    private int command(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = EntityArgumentType.getPlayer(context, "person");
        System.out.println("Got player " + player);
        boolean i = true;
        for (RegistryEntry<StatusEffect> effect : ModEffects.EFFECTS) {
            if (player.hasStatusEffect(effect)) {
                i = false;
                context.getSource().sendFeedback(() -> Text.literal("They have the ")
                        .append(effect.value().getName())
                        .append(" type!"),
                        false);
            }
        }

        if (i) { context.getSource().sendFeedback(() -> Text.literal("They have no type!"), false); }

        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("type")
                        .then(CommandManager.argument("person", EntityArgumentType.player())
                        .executes(this::command)));
    }

}