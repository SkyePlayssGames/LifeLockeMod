package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.entity.ai.HealBlockGoal;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.EnumSet;

public class FireMobEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int healingCooldownTicks = 0;

    private static final int MAX_HEALTH = 15;
    private static final float MOVEMENT_SPEED = 0.3f;
    private static final int ATTACK_DAMAGE = 4;
    private static final int FOLLOW_RANGE = 32;
    private static final int BURNING_TIME = 0;

    private static final BlockState[] HEALING_BLOCKS = Blocks.FIRE.getStateManager().getStates().toArray(new BlockState[0]);

    public FireMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new HealBlockGoal(this, 2, HEALING_BLOCKS));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5, false));
        this.goalSelector.add(3, new WanderAroundGoal(this, 1.0));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, MAX_HEALTH)
                .add(EntityAttributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(EntityAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(EntityAttributes.FOLLOW_RANGE, FOLLOW_RANGE)
                .add(EntityAttributes.BURNING_TIME, BURNING_TIME);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getEntityWorld().isClient()) {
            this.setupAnimationStates();
        } else {
            if (Blocks.FIRE.getStateManager().getStates().contains(this.getEntityWorld().getBlockState(this.getBlockPos())) &&
                this.checkHealingCooldown()) {
                this.heal(2);
            }
        }
    }

    private boolean checkHealingCooldown() {
        if (this.healingCooldownTicks <= 0) {
            this.healingCooldownTicks = 10;
            return true;
        } else {
            this.healingCooldownTicks--;
            return false;
        }
    }


}
