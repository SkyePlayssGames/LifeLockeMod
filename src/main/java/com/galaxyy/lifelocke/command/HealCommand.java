package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class HealCommand implements CommandRegistrationCallback {
    private static final float DAMAGE_RATIO = 2f;

    private int command(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        final PlayerEntity user = context.getSource().getPlayer();
        final PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
        final int amount = IntegerArgumentType.getInteger(context, "amount");
        final ServerWorld world = context.getSource().getWorld();

        if (user == null || !user.hasStatusEffect(ModEffects.FAIRY)) {
            throw new SimpleCommandExceptionType(Text.translatable("text.lifelocke.command_error.heal.not_fairy")).create();
        }

        user.damage(world, ModDamageTypes.of(world, ModDamageTypes.FAIRY_HEAL), amount*DAMAGE_RATIO);
        player.heal(amount);

        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("heal")
                .then(CommandManager.argument("player", EntityArgumentType.player())
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer(0, 20))
                        .executes(this::command)
                )));
    }
}
