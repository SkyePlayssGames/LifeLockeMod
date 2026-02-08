package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.entity.ai.FlyingMonster;
import com.galaxyy.lifelocke.entity.ai.RandomFlyAroundGoal;
import com.galaxyy.lifelocke.entity.ai.StayNearTrialGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static com.galaxyy.lifelocke.entity.ai.PathfindHelper.findNearestFloor;
import static com.galaxyy.lifelocke.entity.ai.PathfindHelper.makeSpeedVec;

public class PsychicMobEntity extends FlyingMonster {
    private static final double FLYING_SPEED = 0.18;
    private static final double MAX_HEALTH = 20;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public PsychicMobEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FLYING_SPEED, FLYING_SPEED)
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new StayNearTrialGoal(this, 20, 3, 10, 1.5f));
        this.goalSelector.addGoal(2, new FuckYouGoal(this));
        this.goalSelector.addGoal(3, new RandomFlyAroundGoal(this, 2, 8));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
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

    private void tick_server() {

    }

    private static class FuckYouGoal extends Goal {
        private enum Mode {
            FLEEING,
            COOLDOWN,
            ATTACKING
        }

        private final PsychicMobEntity mob;
        private Mode mode;
        private int attackCooldownTicks = 0;
        private int healCooldownTicks = 0;

        private FuckYouGoal(PsychicMobEntity mob) {
            this.mob = mob;
        }

        @Override
        public boolean canUse() {
            return this.mob.getTarget() != null && this.mob.getNavigation().isDone();
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.mob.getTarget() == null) { return; }
            set_mode();
            System.out.println("\nMode: " + this.mode + "\nCooldown: " + this.attackCooldownTicks);
            switch (this.mode) {
                case FLEEING: flee(); break;
                case COOLDOWN: cooldown(); break;
                case ATTACKING: attack(); break;
            }
        }

        private void set_mode() {
            assert this.mob.getTarget() != null;

            if (shouldFlee()) {
                this.mode = Mode.FLEEING;
            } else if (attackCooldownTicks > 0) {
                this.mode = Mode.COOLDOWN;
            } else {
                this.mode = Mode.ATTACKING;
            }
        }

        private boolean shouldFlee() {
            return Math.sqrt(this.mob.distanceToSqr(this.mob.getTarget())) < 6;
        }

        private void flee() {
            LivingEntity target = this.mob.getTarget();
            assert target != null;

            this.mob.getNavigation().moveTo(
                    this.mob.getX() - Math.clamp(target.getX() - this.mob.getX(), -1, 1),
                    this.mob.getY() - Math.clamp(target.getY() - this.mob.getY(),
                            findNearestFloor(this.mob.level(), this.mob.blockPosition()) < 4 ?  -1 : 0.5, 1),
                    this.mob.getZ() - Math.clamp(target.getZ() - this.mob.getZ(), -1, 1),
                    1.5
            );

            attackCooldownTicks--;
        }

        private void cooldown() {
            this.mob.getNavigation().stop();
            attackCooldownTicks--;
            healCooldownTicks--;
            if (healCooldownTicks <= 0) {
                this.mob.heal(2);
                healCooldownTicks = 40;
            }
        }

        private void attack() {
            LivingEntity target = this.mob.getTarget();
            assert target != null;

            target.hurtServer(((ServerLevel) this.mob.level()),
                    new DamageSource(this.mob.level().registryAccess().getOrThrow(DamageTypes.MOB_ATTACK), this.mob),
                    4);

            attackCooldownTicks = 40;
        }
    }
}
