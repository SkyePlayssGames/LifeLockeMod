package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.entity.ai.FlyingMonster;
import com.galaxyy.lifelocke.entity.ai.RandomFlyAroundGoal;
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
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public PsychicMobEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FLYING_SPEED, 0.2)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);
    }

    @Override
    public PathNavigation getNavigation() {
        return new FlyingPathNavigation(this, this.level());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FuckYouGoal(this));
        this.goalSelector.addGoal(2, new RandomFlyAroundGoal(this, 0.05));

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
            return this.mob.getTarget() != null;
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
            return Math.sqrt(this.mob.distanceToSqr(this.mob.getTarget())) < 6 && !pathBlocked();
        }

        private boolean pathBlocked() {
            LivingEntity target = this.mob.getTarget();
            Level level = this.mob.level();

            assert target != null;
            Vec3 toTarget = makeSpeedVec(this.mob, target.getX(), target.getY(), target.getZ(), 1);
            Vec3 fromTarget = new Vec3(-toTarget.x(), -toTarget.y(), -toTarget.z());

            if (fromTarget.x < 0) {
                if (level.getBlockState(this.mob.blockPosition().west()).is(BlockTags.AIR)) {
                    return false;
                }
            } else {
                if (level.getBlockState(this.mob.blockPosition().east()).is(BlockTags.AIR)) {
                    return false;
                }
            }
            if (fromTarget.z < 0) {
                if (level.getBlockState(this.mob.blockPosition().north()).is(BlockTags.AIR)) {
                    return false;
                }
            } else {
                if (level.getBlockState(this.mob.blockPosition().south()).is(BlockTags.AIR)) {
                    return false;
                }
            }
            return true;
        }

        private void flee() {
            LivingEntity target = this.mob.getTarget();
            assert target != null;
            Vec3 toTarget = makeSpeedVec(this.mob, target.getX(), target.getY(), target.getZ(), 1.5);
            Vec3 fromTarget;
            if (findNearestFloor(this.mob.level(), this.mob.blockPosition()) < 4) {
                fromTarget = new Vec3(-toTarget.x, -toTarget.y, -toTarget.z);
            } else {
                fromTarget = new Vec3(-toTarget.x, -Math.abs(toTarget.y), -toTarget.z);
            }
            this.mob.setTargetSpeed(fromTarget);

            attackCooldownTicks--;
        }

        private void cooldown() {
            this.mob.setTargetSpeed(Vec3.ZERO);
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

            this.mob.setTargetSpeed(makeSpeedVec(this.mob, target.getX(), target.getY() + 2.5, target.getZ(), 0.5));

            attackCooldownTicks = 40;
        }
    }
}
