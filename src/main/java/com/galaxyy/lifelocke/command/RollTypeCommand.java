package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.gamerule.ModGameRules;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import java.util.Arrays;

public class RollTypeCommand implements CommandRegistrationCallback {
    private int command(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = context.getSource().getPlayer();
        boolean type_duplication = context.getSource().getLevel().getGameRules().get(ModGameRules.TYPE_DUPLICATION);
        boolean special_types = context.getSource().getLevel().getGameRules().get(ModGameRules.SPECIAL_TYPE_ROLL);
        Holder<MobEffect>[] effectsList = special_types ? ModEffects.EFFECTS : ModEffects.ROLLABLE_EFFECTS;

        if (player == null) {
            throw new SimpleCommandExceptionType(Component.translatable("text.lifelocke.command_error.rolltype.not_player_sent")).create();
        }
        for (Holder<MobEffect> effect : ModEffects.EFFECTS) {
            if (player.hasEffect(effect)) {
                throw new SimpleCommandExceptionType(Component.translatable("text.lifelocke.command_error.rolltype.already_has_type")).create();
            }
        }

        int[] types_had = UpdateData.getTypeList((iEntityDataSaver) player);
        if (types_had.length >= effectsList.length && !type_duplication) {
            throw new SimpleCommandExceptionType(Component.translatable("text.lifelocke.command_error.rolltype.has_had_all_types")).create();
        }

        if (!UpdateData.getAndSetRolltypeConfirmation(((iEntityDataSaver) player))) {
            context.getSource().sendSuccess(() -> Component.translatable("text.lifelocke.command.rolltype.confirm"), false);
            return 0;
        }

        player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Blocks.AIR));
        player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Blocks.AIR));
        player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Blocks.AIR));
        player.setItemSlot(EquipmentSlot.FEET, new ItemStack(Blocks.AIR));

        while (true) {
            int type_rolled = player.getRandom().nextIntBetweenInclusive(0, effectsList.length-1);
            if (Arrays.stream(types_had).anyMatch(value -> value == type_rolled) && !type_duplication) {
                continue;
            }

            if (!type_duplication) {
                int[] types_have = new int[types_had.length + 1];
                int i = 0;
                while (i < types_had.length) {
                    types_have[i] = types_had[i];
                    i++;
                }
                types_have[types_had.length] = type_rolled;

                UpdateData.setTypeList(((iEntityDataSaver) player), types_have);
            }
            
            player.addEffect(new MobEffectInstance(effectsList[type_rolled], -1));

            for (Player playerEntity : context.getSource().getLevel().players()) {
                playerEntity.displayClientMessage(Component.translatable("text.lifelocke.command.rolltype.rolled_type",
                        player.getName(), effectsList[type_rolled].value().getDisplayName()), false);
            }

            break;
        }

        return 1;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        commandDispatcher.register(Commands.literal("rolltype")
                .executes(this::command));
    }

}