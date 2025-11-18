package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class DebugCommand implements CommandRegistrationCallback {
    private int resetTypes(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        UpdateData.setTypeList(((iEntityDataSaver) context.getSource().getPlayerOrThrow()), new int[] {});
        context.getSource().sendFeedback(() -> Text.translatable("text.lifelocke.command.debug.reset_types"), false);
        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("lifelocke_debug")
                .then(CommandManager.literal("reset_types")
                        .requires(source -> source.hasPermissionLevel(1))
                        .executes(this::resetTypes)));
    }
}
