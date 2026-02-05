package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class HealCommand implements CommandRegistrationCallback {
    private static final float DAMAGE_RATIO = 2f;

    private int command(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final Player user = context.getSource().getPlayer();
        final Player player = EntityArgument.getPlayer(context, "player");
        final int amount = IntegerArgumentType.getInteger(context, "amount");
        final ServerLevel world = context.getSource().getLevel();

        if (user == null || !user.hasEffect(Types.FAIRY_TYPE.type)) {
            throw new SimpleCommandExceptionType(Component.translatable("text.lifelocke.command_error.heal.not_fairy")).create();
        }

        user.hurtServer(world, ModDamageTypes.of(world, ModDamageTypes.FAIRY_HEAL), amount*DAMAGE_RATIO);
        player.heal(amount);

        return 1;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        commandDispatcher.register(Commands.literal("heal")
                .then(Commands.argument("player", EntityArgument.player())
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0, 20))
                        .executes(this::command)
                )));
    }
}
