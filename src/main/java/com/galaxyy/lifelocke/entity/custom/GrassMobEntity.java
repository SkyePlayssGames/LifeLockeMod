package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.entity.ai.PathfindHelper;
import com.galaxyy.lifelocke.entity.ai.HideBlockGoal;
import com.galaxyy.lifelocke.entity.ai.StayNearTrialGoal;
import com.galaxyy.lifelocke.networking.GrassMobAnimationS2CPayload;
import com.galaxyy.lifelocke.tags.ModTags;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GrassMobEntity extends Monster {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.25f;
    private static final int ATTACK_DAMAGE = 3;
    private static final int FOLLOW_RANGE = 16;
    private static final int GRASS_ATTACK_DAMAGE = 6;

    private static final EntityDimensions HIDDEN_DIMENSIONS = EntityDimensions.scalable(0.8f, 1.05f);

    private static final TagKey<Block> HIDEABLE_BLOCKS = ModTags.GRASS_MOB_HIDE;
    private static final TagKey<Block> ATTACKABLE_BLOCKS = ModTags.GRASS_MOB_ATTACK;
    private static final Identifier HIDDEN_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "hidden");

    private int grassAttackCooldownTicks = 0;

    public boolean playHidingAnimation = false;
    public boolean playUnhidingAnimation = false;
    public boolean playMagicAttackAnimation = false;

    public final AnimationState hidingAnimationState = new AnimationState();
    public final AnimationState unhidingAnimationState = new AnimationState();
    public final AnimationState magicAttackAnimationState = new AnimationState();

    private static final EntityDataAccessor<Boolean> HIDDEN = SynchedEntityData.defineId(
            GrassMobEntity.class, EntityDataSerializers.BOOLEAN);

    public GrassMobEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new StayNearTrialGoal(this, 12, 3, 7, 1.6f));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.00, true));
        this.goalSelector.addGoal(3, new HideBlockGoal(this, HIDEABLE_BLOCKS, 1.00, 5));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, FOLLOW_RANGE);
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide()) {
            tick_client();
        } else {
            tick_server();
        }

    }

    private void tick_client() {

        if (playHidingAnimation) {
            stop_animations();
            hidingAnimationState.start(this.tickCount);
            playHidingAnimation = false;
        }

        if (playUnhidingAnimation) {
            stop_animations();
            unhidingAnimationState.start(this.tickCount);
            playUnhidingAnimation = false;
        }

        if (playMagicAttackAnimation) {
            stop_animations();
            magicAttackAnimationState.start(this.tickCount);
            playMagicAttackAnimation = false;
        }
    }

    private void stop_animations() {
        hidingAnimationState.stop();
        unhidingAnimationState.stop();
        magicAttackAnimationState.stop();
    }

    private void tick_server() {

        AttributeInstance followAttribute = Objects.requireNonNull(this.getAttributes().getInstance(Attributes.FOLLOW_RANGE));
        Vec3 speed = this.getDeltaMovement();

        if (speed.x < 0.1d && speed.z < 0.1d && PathfindHelper.isTouchingBlock(this, HIDEABLE_BLOCKS)) {
            if (!followAttribute.hasModifier(HIDDEN_ID)) {
                followAttribute.addTransientModifier(
                        new AttributeModifier(HIDDEN_ID, -11, AttributeModifier.Operation.ADD_VALUE)
                );
                sendAnimationPacket(null, GrassMobAnimationS2CPayload.ANIMATION.HIDE);
                this.entityData.set(HIDDEN, true);
            }

            handleGrassAttack();

        } else if (!PathfindHelper.isTouchingBlock(this, HIDEABLE_BLOCKS) && followAttribute.hasModifier(HIDDEN_ID)) {
            followAttribute.removeModifier(HIDDEN_ID);
            sendAnimationPacket(null, GrassMobAnimationS2CPayload.ANIMATION.UNHIDE);
            this.entityData.set(HIDDEN, false);
        }
    }

    public void handleGrassAttack() {
        ServerLevel world = (ServerLevel) level();
        if (grassAttackCooldownTicks <= 0) {
            boolean hitAnyone = false;

            for (ServerPlayer player : world.players()) {
                if (world.getBlockState(player.blockPosition().below()).is(ATTACKABLE_BLOCKS) &&
                        this.hasLineOfSight(player) &&
                        (player.gameMode() == GameType.SURVIVAL || player.gameMode() == GameType.ADVENTURE)
                ) {
                    hitAnyone = true;
                    sendAnimationPacket(player, GrassMobAnimationS2CPayload.ANIMATION.MAGIC_ATTACK);
                    player.hurtServer(world, ModDamageTypes.of(world, ModDamageTypes.PLANT_ATTACK, this),
                            GRASS_ATTACK_DAMAGE);
                }
            }
            if (hitAnyone) {
                grassAttackCooldownTicks = 40;
            }
        } else {
            grassAttackCooldownTicks--;
        }
    }

    private void sendAnimationPacket(@Nullable ServerPlayer player, GrassMobAnimationS2CPayload.ANIMATION animation) {
        if (player != null) {
            ServerPlayNetworking.send(player, new GrassMobAnimationS2CPayload(this.uuid, animation.getSerializedName()));
        } else {
            for (ServerPlayer playerEntity : ((ServerLevel) level()).players()) {
                ServerPlayNetworking.send(playerEntity, new GrassMobAnimationS2CPayload(this.uuid, animation.getSerializedName()));
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(HIDDEN, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (HIDDEN.equals(entityDataAccessor)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(entityDataAccessor);
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        //System.out.println("Setting dimensions!\n" + hidden + "\n" +
        //        (this.hidden ? HIDDEN_DIMENSIONS.height() : super.getDefaultDimensions(pose).height()));
        return entityData.get(HIDDEN) ? HIDDEN_DIMENSIONS : super.getDefaultDimensions(pose);
    }
}
