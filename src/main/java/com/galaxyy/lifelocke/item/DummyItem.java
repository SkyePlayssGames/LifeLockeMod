package com.galaxyy.lifelocke.item;

import java.util.function.UnaryOperator;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.block.TeraTrialBlockEntity;
import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import com.galaxyy.lifelocke.rendering.particles.ModParticles;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class DummyItem extends Item {
    public DummyItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (useOnContext.getLevel().isClientSide()) return InteractionResult.PASS;

        ServerLevel level = (ServerLevel) useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        ItemStack item = useOnContext.getItemInHand();

        if (level.getBlockState(blockPos).getBlock() == Blocks.TRIAL_SPAWNER) {
            item.set(ModDataComponents.DEBUG_BLOCKPOS, blockPos);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(blockPos).getBlock() == ModBlocks.TERA_TRIAL_BLOCK &&
                item.get(ModDataComponents.DEBUG_BLOCKPOS) != null) {
            ((TeraTrialBlockEntity) level.getBlockEntity(blockPos)).appendSpawnerPosition(
                    item.get(ModDataComponents.DEBUG_BLOCKPOS)
            );
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
