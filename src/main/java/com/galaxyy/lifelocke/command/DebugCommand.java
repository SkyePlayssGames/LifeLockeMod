package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.world.effect.MobEffect;
import java.util.Collection;

public class DebugCommand implements CommandRegistrationCallback {
    private int resetTypes(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
        for (ServerPlayer player : players) {
            UpdateData.setTypeList(((iEntityDataSaver) player), new int[]{});
            player.displayClientMessage(Component.translatable("text.lifelocke.command.debug.reset_types"), false);
        }
        return 1;
    }

    private int addType(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
        String effect = StringArgumentType.getString(context, "effect");
        int i = 0;
        while (i < Types.TYPE_EFFECTS.size()) {
            Holder<MobEffect> statusEffect = Types.TYPE_EFFECTS.get(i);
            if (statusEffect.is(Identifier.parse(effect))) {
                break;
            }
            i++;
        }

        if (i == Types.TYPE_EFFECTS.size()) {
            throw new SimpleCommandExceptionType(Component.translatable("text.lifelocke.command_error.debug.not_a_type")).create();
        }

        for (ServerPlayer player : players) {
            int[] types_had = UpdateData.getTypeList(((iEntityDataSaver) player));

            int[] types_have = new int[types_had.length+1];
            int j = 0;
            while (j < types_had.length) {
                types_have[j] = types_had[j];
                j++;
            }
            types_have[types_had.length] = i;

            UpdateData.setTypeList(((iEntityDataSaver) player), types_have);
            int finalI = i;
            player.displayClientMessage(Component.translatable("text.lifelocke.command.debug.added_type", ((MobEffect) Types.TYPE_EFFECTS.get(finalI).value()).getDisplayName()), false);
        }
        return 1;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        commandDispatcher.register(Commands.literal("lifelocke_debug")
                .requires(source -> source.permissions().hasPermission(new Permission.HasCommandLevel(PermissionLevel.MODERATORS)))
                .then(Commands.literal("reset_types")
                        .then(Commands.argument("player", EntityArgument.players())
                                .executes(this::resetTypes)))
                .then(Commands.literal("add_type")
                        .then(Commands.argument("player", EntityArgument.players())
                                .then(Commands.argument("effect", StringArgumentType.string())
                                        .executes(this::addType))))

        );
    }
}
