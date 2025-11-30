package com.galaxyy.lifelocke.command;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.gamerule.ModGameRules;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.Arrays;

public class RollTypeCommand implements CommandRegistrationCallback {
    private int command(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = context.getSource().getPlayer();
        boolean type_duplication = context.getSource().getWorld().getGameRules().getBoolean(ModGameRules.TYPE_DUPLICATION);
        if (player == null) {
            throw new SimpleCommandExceptionType(Text.translatable("text.lifelocke.command_error.rolltype.not_player_sent")).create();
        }
        for (RegistryEntry<StatusEffect> effect : ModEffects.EFFECTS) {
            if (player.hasStatusEffect(effect)) {
                throw new SimpleCommandExceptionType(Text.translatable("text.lifelocke.command_error.rolltype.already_has_type")).create();
            }
        }

        int[] types_had = UpdateData.getTypeList((iEntityDataSaver) player);
        if (types_had.length >= ModEffects.ROLLABLE_EFFECTS.length && !type_duplication) {
            throw new SimpleCommandExceptionType(Text.translatable("text.lifelocke.command_error.rolltype.has_had_all_types")).create();
        }

        if (!UpdateData.getAndSetRolltypeConfirmation(((iEntityDataSaver) player))) {
            context.getSource().sendFeedback(() -> Text.translatable("text.lifelocke.command.rolltype.confirm"), false);
            return 0;
        }

        player.equipStack(EquipmentSlot.HEAD, new ItemStack(Blocks.AIR));
        player.equipStack(EquipmentSlot.CHEST, new ItemStack(Blocks.AIR));
        player.equipStack(EquipmentSlot.LEGS, new ItemStack(Blocks.AIR));
        player.equipStack(EquipmentSlot.FEET, new ItemStack(Blocks.AIR));

        while (true) {
            int type_rolled = player.getRandom().nextBetween(0, ModEffects.ROLLABLE_EFFECTS.length-1);
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
            
            player.addStatusEffect(new StatusEffectInstance(ModEffects.ROLLABLE_EFFECTS[type_rolled], -1));

            for (PlayerEntity playerEntity : context.getSource().getWorld().getPlayers()) {
                playerEntity.sendMessage(Text.translatable("text.lifelocke.command.rolltype.rolled_type",
                        player.getName(), ((StatusEffect) ModEffects.ROLLABLE_EFFECTS[type_rolled].value()).getName()), false);
            }

            break;
        }

        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("rolltype")
                .executes(this::command));
    }

}