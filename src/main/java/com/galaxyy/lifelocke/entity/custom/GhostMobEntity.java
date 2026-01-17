package com.galaxyy.lifelocke.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SyncedDataHolder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import static net.minecraft.world.entity.EntityEvent.START_ATTACKING;

public class GhostMobEntity extends Monster implements SyncedDataHolder {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.28f;
    private static final int ATTACK_DAMAGE = 40;

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
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        //goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        //goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Pig.class, true));
    }

    public static AttributeSupplier.@NonNull Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE);
    }

    @Override
    public void handleEntityEvent(byte entityStatus) {
        System.out.println("Handle Entity Event: " + entityStatus);
        switch (entityStatus) {
            case 4 -> {
                attackAnimationState.start(tickCount);
                System.out.println("Attacking!");
            }
            default -> super.handleEntityEvent(entityStatus);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(StartAttackAnim, 0);
        super.defineSynchedData(builder);
    }

    @Override
    protected void playAttackSound() {
        super.playAttackSound();
        System.out.println("Attacking!");
        System.out.println("Animating? " + this.entityData.get(StartAttackAnim));
        this.entityData.set(StartAttackAnim, this.tickCount);
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

    }

    private void stopAllAnimations() {
        attackAnimationState.stop();
    }
}
