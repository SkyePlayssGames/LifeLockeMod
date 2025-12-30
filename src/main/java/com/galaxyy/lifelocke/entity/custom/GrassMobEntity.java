package com.galaxyy.lifelocke.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GrassMobEntity extends HostileEntity {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.25f;
    private static final int ATTACK_DAMAGE = 3;
    private static final int FOLLOW_RANGE = 16;

    public GrassMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.00, true));
        this.goalSelector.add(3, new WanderAroundGoal(this, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, MAX_HEALTH)
                .add(EntityAttributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(EntityAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(EntityAttributes.FOLLOW_RANGE, FOLLOW_RANGE);
    }
}
