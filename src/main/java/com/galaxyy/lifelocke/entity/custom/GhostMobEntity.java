package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.entity.ai.StayNearTrialGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SyncedDataHolder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import static net.minecraft.world.entity.EntityEvent.START_ATTACKING;

public class GhostMobEntity extends Monster implements SyncedDataHolder {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.28f;
    private static final int ATTACK_DAMAGE = 4;
    public static final int ATTACK_ANIM_TICKS = 6;

    private static final EntityDataAccessor<Integer> StartAttackAnim = SynchedEntityData.defineId(
            GhostMobEntity.class, EntityDataSerializers.INT);
    private int lastAttack = 0;

    public final AnimationState attackAnimationState = new AnimationState();

    public GhostMobEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new StayNearTrialGoal(this, 15, 2, 10, 1.4f));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, true));
        goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.@NonNull Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(StartAttackAnim, 0);
        super.defineSynchedData(builder);
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity entity) {
        this.entityData.set(StartAttackAnim, this.tickCount);
        this.removeEffect(MobEffects.INVISIBILITY);
        return super.doHurtTarget(serverLevel, entity);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            tickClient();
        } else {
            tickServer();
        }
    }

    private void tickClient() {
        if (this.entityData.get(StartAttackAnim) > this.lastAttack) {
            System.out.println("Animating!");
            stopAllAnimations();
            attackAnimationState.start(this.tickCount);
            this.lastAttack = this.entityData.get(StartAttackAnim);
        }
    }

    private void tickServer() {
        if (this.tickCount == this.entityData.get(StartAttackAnim) + ATTACK_ANIM_TICKS || this.entityData.get(StartAttackAnim) == 0) {
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, -1, 0, false, false));
        }
    }

    private void stopAllAnimations() {
        attackAnimationState.stop();
    }


}
