package com.galaxyy.lifelocke.entity.custom;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.ai.BlockFinder;
import com.galaxyy.lifelocke.entity.ai.HideBlockGoal;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Objects;

public class GrassMobEntity extends HostileEntity {
    private static final int MAX_HEALTH = 20;
    private static final float MOVEMENT_SPEED = 0.25f;
    private static final int ATTACK_DAMAGE = 3;
    private static final int FOLLOW_RANGE = 16;

    private static final ImmutableList<BlockState> HIDEABLE_BLOCKS = Blocks.SHORT_GRASS.getStateManager().getStates();
    private static final Identifier HIDDEN_ID = Identifier.of(LifeLocke.MOD_ID, "hidden");

    public GrassMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.00, true));
        this.goalSelector.add(2, new HideBlockGoal(this, HIDEABLE_BLOCKS, 1.00, 8));
        this.goalSelector.add(3, new LookAroundGoal(this));

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, MAX_HEALTH)
                .add(EntityAttributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(EntityAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(EntityAttributes.FOLLOW_RANGE, FOLLOW_RANGE);
    }

    @Override
    public void tick() {
        super.tick();

        EntityAttributeInstance followAttribute = Objects.requireNonNull(this.getAttributes().getCustomInstance(EntityAttributes.FOLLOW_RANGE));

        if (!this.isNavigating() && BlockFinder.isTouchingBlock(this, HIDEABLE_BLOCKS)) {
            if (!followAttribute.hasModifier(HIDDEN_ID)) {
                followAttribute.addTemporaryModifier(
                        new EntityAttributeModifier(HIDDEN_ID, -11, EntityAttributeModifier.Operation.ADD_VALUE)
                );
            }
        } else if (followAttribute.hasModifier(HIDDEN_ID)) {
            followAttribute.removeModifier(HIDDEN_ID);
        }
    }
}
