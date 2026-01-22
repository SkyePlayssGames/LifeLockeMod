package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.entity.ai.RandomFlyAroundGoal;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PsychicMobEntity extends Monster {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public PsychicMobEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FLYING_SPEED, 0.15)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);
    }

    @Override
    public PathNavigation getNavigation() {
        return new FlyingPathNavigation(this, this.level());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomFlyAroundGoal(this, 0.05));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Pig.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            tick_client();
        } else {
            tick_server();
        }
    }

    private void tick_client() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationState.start(this.tickCount);
            this.idleAnimationTimeout = 20;
        } else {
            this.idleAnimationTimeout--;
        }
    }

    private void tick_server() {}
}
