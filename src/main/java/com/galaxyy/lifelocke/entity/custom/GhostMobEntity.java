package com.galaxyy.lifelocke.entity.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import static net.minecraft.world.entity.EntityEvent.START_ATTACKING;

public class GhostMobEntity extends Monster {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.35f;
    private static final int ATTACK_DAMAGE = 4;

    public final AnimationState attackAnimationState = new AnimationState();

    public GhostMobEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
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
}
