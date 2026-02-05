package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PsychicCommand implements net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback {
    private int command(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (!context.getSource().getPlayer().hasEffect(Types.PSYCHIC_TYPE.type)) {
            throw new SimpleCommandExceptionType(Component.translatable("text.lifelocke.command_error.psychic.not_psychic")).create();
        }

        Player player = EntityArgument.getPlayer(context, "person");
        context.getSource().sendSuccess(() -> Component.translatable("text.lifelocke.command.psychic.psychic",
                player.getName(), player.getBlockX(), player.getBlockY(), player.getBlockZ()), false);

        return 1;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        commandDispatcher.register(Commands.literal("psychic")
                        .then(Commands.argument("person", EntityArgument.player())
                        .executes(this::command)));
    }

}