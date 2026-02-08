package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.entity.ai.HealBlockGoal;
import com.galaxyy.lifelocke.entity.ai.StayNearTrialGoal;
import com.galaxyy.lifelocke.tags.ModTags;
import net.minecraft.tags.TagKey;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class FireMobEntity extends Monster {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int healingCooldownTicks = 0;

    private static final int MAX_HEALTH = 15;
    private static final float MOVEMENT_SPEED = 0.3f;
    private static final int ATTACK_DAMAGE = 4;
    private static final int FOLLOW_RANGE = 32;
    private static final int BURNING_TIME = 0;

    private static final TagKey<Block> HEALING_BLOCKS = ModTags.FIRE_MOB_HEAL;

    public FireMobEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new StayNearTrialGoal(this, 12, 3, 8, 1.5f));
        this.goalSelector.addGoal(2, new HealBlockGoal(this, 2, HEALING_BLOCKS));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.5, false));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, FOLLOW_RANGE)
                .add(Attributes.BURNING_TIME, BURNING_TIME);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        } else {
            if (Blocks.FIRE.getStateDefinition().getPossibleStates().contains(this.level().getBlockState(this.blockPosition())) &&
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
