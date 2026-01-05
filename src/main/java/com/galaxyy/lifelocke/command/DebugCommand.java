package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.permission.Permission;
import net.minecraft.command.permission.PermissionLevel;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Collection;

public class DebugCommand implements CommandRegistrationCallback {
    private int resetTypes(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "player");
        for (ServerPlayerEntity player : players) {
            UpdateData.setTypeList(((iEntityDataSaver) player), new int[]{});
            player.sendMessage(Text.translatable("text.lifelocke.command.debug.reset_types"), false);
        }
        return 1;
    }

    private int addType(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "player");
        String effect = StringArgumentType.getString(context, "effect");
        int i = 0;
        while (i < ModEffects.EFFECTS.length) {
            RegistryEntry<StatusEffect> statusEffect = ModEffects.EFFECTS[i];
            if (statusEffect.matchesId(Identifier.of(effect))) {
                break;
            }
            i++;
        }

        if (i == ModEffects.EFFECTS.length) {
            throw new SimpleCommandExceptionType(Text.translatable("text.lifelocke.command_error.debug.not_a_type")).create();
        }

        for (ServerPlayerEntity player : players) {
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
            player.sendMessage(Text.translatable("text.lifelocke.command.debug.added_type", ((StatusEffect) ModEffects.EFFECTS[finalI].value()).getName()), false);
        }
        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("lifelocke_debug")
                .requires(source -> source.getPermissions().hasPermission(new Permission.Level(PermissionLevel.MODERATORS)))
                .then(CommandManager.literal("reset_types")
                        .then(CommandManager.argument("player", EntityArgumentType.players())
                                .executes(this::resetTypes)))
                .then(CommandManager.literal("add_type")
                        .then(CommandManager.argument("player", EntityArgumentType.players())
                                .then(CommandManager.argument("effect", StringArgumentType.string())
                                        .executes(this::addType))))

        );
    }
}
