package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class PsychicCommand implements net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback {
    private int command(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (!context.getSource().getPlayer().hasStatusEffect(ModEffects.PSYCHIC)) {
            throw new SimpleCommandExceptionType(Text.translatable("text.lifelocke.command_error.psychic.not_psychic")).create();
        }

        PlayerEntity player = EntityArgumentType.getPlayer(context, "person");
        context.getSource().sendFeedback(() -> Text.translatable("text.lifelocke.command.psychic.psychic",
                player.getName(), player.getBlockX(), player.getBlockY(), player.getBlockZ()), false);

        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("psychic")
                        .then(CommandManager.argument("person", EntityArgumentType.player())
                        .executes(this::command)));
    }

}