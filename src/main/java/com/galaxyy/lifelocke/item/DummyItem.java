package com.galaxyy.lifelocke.item;

import java.util.function.UnaryOperator;

import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.rendering.particles.ModParticles;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class DummyItem extends Item {
    public DummyItem(Properties settings) {
        super(settings);
    }

    private static void showBeam(Level level, BlockPos origin, BlockPos hit) {
        int x = origin.getX();
        int y = origin.getY();
        int z = origin.getZ();
        x -= hit.getX();
        y -= hit.getY();
        z -= hit.getZ();

        while (Math.abs(x) < 1 || Math.abs(y) < 1 || Math.abs(z) < 1) {
            x /= 2;
            y /= 2;
            z /= 2;
        }
        int particleX = origin.getX() + x;
        int particleY = origin.getY() + y;
        int particleZ = origin.getZ() + z;

        while (particleX < hit.getX()) {
            level.addParticle(ModParticles.TEST_PARTICLE, particleX, particleY, particleZ, 0, 0, 0);
            particleX += x;
            particleY += y;
            particleZ += z;
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        double maxReach = 1000;
        float tickDelta = 1f;
        boolean liquids = true;

        assert Minecraft.getInstance().getCameraEntity() != null;
        HitResult hit = Minecraft.getInstance().getCameraEntity().pick(maxReach, tickDelta, liquids);

        switch (hit.getType()) {
            case MISS:
                break;
            case BLOCK:
                BlockHitResult blockHitResult = (BlockHitResult) hit;
                BlockPos pos = blockHitResult.getBlockPos();
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 255);
                showBeam(level, player.blockPosition().above(), pos);
                break;
            case ENTITY:
                EntityHitResult entityHitResult = (EntityHitResult) hit;
                Entity entity = entityHitResult.getEntity();
                entity.hurt(ModDamageTypes.of(level, ModDamageTypes.FAIRY_HEAL), 1000);
                break;
        }

        return InteractionResult.SUCCESS;
    }
}
