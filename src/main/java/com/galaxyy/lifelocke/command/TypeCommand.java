package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;

public class TypeCommand implements net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback {
    private int command(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = EntityArgument.getPlayer(context, "person");
        boolean i = true;
        for (Holder<MobEffect> effect : ModEffects.EFFECTS) {
            if (player.hasEffect(effect)) {
                i = false;
                context.getSource().sendSuccess(() -> Component.translatable("text.lifelocke.command.type.has_type", player.getName(), effect.value().getDisplayName()), false);
            }
        }

        if (i) { context.getSource().sendSuccess(() -> Component.translatable("text.lifelocke.command.type.has_no_type", player.getName()), false); }

        return 1;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        commandDispatcher.register(Commands.literal("type")
                        .then(Commands.argument("person", EntityArgument.player())
                        .executes(this::command)));
    }

}