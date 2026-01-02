package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.entity.ai.BlockFinder;
import com.galaxyy.lifelocke.entity.ai.HideBlockGoal;
import com.galaxyy.lifelocke.networking.GrassMobAnimationS2CPayload;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class GrassMobEntity extends HostileEntity {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.25f;
    private static final int ATTACK_DAMAGE = 3;
    private static final int FOLLOW_RANGE = 16;

    private static final BlockState[] HIDEABLE_BLOCKS = Blocks.SHORT_GRASS.getStateManager().getStates().toArray(new BlockState[0]);
    private static final BlockState[] ATTACKABLE_BLOCKS = Blocks.GRASS_BLOCK.getStateManager().getStates().toArray(new BlockState[0]);
    private static final Identifier HIDDEN_ID = Identifier.of(LifeLocke.MOD_ID, "hidden");

    private int grassAttackCooldownTicks = 0;

    public boolean playHidingAnimation = false;
    public boolean playUnhidingAnimation = false;
    public boolean playMagicAttackAnimation = false;

    public final AnimationState hidingAnimationState = new AnimationState();
    public final AnimationState unhidingAnimationState = new AnimationState();
    public final AnimationState magicAttackAnimationState = new AnimationState();

    public GrassMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.00, true));
        this.goalSelector.add(2, new HideBlockGoal(this, HIDEABLE_BLOCKS, 1.00, 8));
        this.goalSelector.add(3, new LookAroundGoal(this));

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, MAX_HEALTH)
                .add(EntityAttributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(EntityAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(EntityAttributes.FOLLOW_RANGE, FOLLOW_RANGE);
    }

    @Override
    public void tick() {
        super.tick();

        if (getEntityWorld().isClient()) {
            tick_client();
        } else {
            tick_server();
        }

    }

    private void tick_client() {

        if (playHidingAnimation) {
            stop_animations();
            hidingAnimationState.start(this.age);
            playHidingAnimation = false;
        }

        if (playUnhidingAnimation) {
            stop_animations();
            unhidingAnimationState.start(this.age);
            playUnhidingAnimation = false;
        }

        if (playMagicAttackAnimation) {
            stop_animations();
            magicAttackAnimationState.start(this.age);
            playMagicAttackAnimation = false;
        }
    }

    private void stop_animations() {
        hidingAnimationState.stop();
        unhidingAnimationState.stop();
        magicAttackAnimationState.stop();
    }

    private void tick_server() {
        EntityAttributeInstance followAttribute = Objects.requireNonNull(this.getAttributes().getCustomInstance(EntityAttributes.FOLLOW_RANGE));
        Vec3d speed = this.getVelocity();

        if (speed.x < 0.1d && speed.z < 0.1d && BlockFinder.isTouchingBlock(this, HIDEABLE_BLOCKS)) {
            if (!followAttribute.hasModifier(HIDDEN_ID)) {
                followAttribute.addTemporaryModifier(
                        new EntityAttributeModifier(HIDDEN_ID, -11, EntityAttributeModifier.Operation.ADD_VALUE)
                );
                sendAnimationPacket(null, GrassMobAnimationS2CPayload.ANIMATION.HIDE);
            }

            handleGrassAttack();

        } else if (!BlockFinder.isTouchingBlock(this, HIDEABLE_BLOCKS) && followAttribute.hasModifier(HIDDEN_ID)) {
            followAttribute.removeModifier(HIDDEN_ID);
            sendAnimationPacket(null, GrassMobAnimationS2CPayload.ANIMATION.UNHIDE);
        }

    }

    public void handleGrassAttack() {
        ServerWorld world = (ServerWorld) getEntityWorld();
        if (grassAttackCooldownTicks <= 0) {
            boolean hitAnyone = false;

            for (ServerPlayerEntity player : world.getPlayers()) {
                if (Arrays.stream(ATTACKABLE_BLOCKS).anyMatch(Predicate.isEqual(world.getBlockState(player.getBlockPos().down()))) &&
                        this.canSee(player) &&
                        (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE)
                ) {
                    hitAnyone = true;
                    sendAnimationPacket(player, GrassMobAnimationS2CPayload.ANIMATION.MAGIC_ATTACK);
                    player.damage(world, ModDamageTypes.of(world, ModDamageTypes.PLANT_ATTACK, this), 2);
                }
            }
            if (hitAnyone) {
                grassAttackCooldownTicks = 40;
            }
        } else {
            grassAttackCooldownTicks--;
        }
    }

    private void sendAnimationPacket(@Nullable ServerPlayerEntity player, GrassMobAnimationS2CPayload.ANIMATION animation) {
        if (player != null) {
            ServerPlayNetworking.send(player, new GrassMobAnimationS2CPayload(this.uuid, animation.asString()));
        } else {
            for (ServerPlayerEntity playerEntity : ((ServerWorld) getEntityWorld()).getPlayers()) {
                ServerPlayNetworking.send(playerEntity, new GrassMobAnimationS2CPayload(this.uuid, animation.asString()));
            }
        }
    }
}
