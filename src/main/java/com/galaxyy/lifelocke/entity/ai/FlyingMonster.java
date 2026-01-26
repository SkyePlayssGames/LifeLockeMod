package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class FlyingMonster extends Monster {
    protected FlyingMonster(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    private static final double acceleration = 0.025;
    private Vec3 targetSpeed = Vec3.ZERO;

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            accelerate();
        }
    }

    private void accelerate() {
        Vec3 speed = this.getDeltaMovement();
        double x, y, z;

        if (targetSpeed.x > speed.x) {
            x = Math.min(targetSpeed.x, speed.x + acceleration);
        } else if (targetSpeed.x < speed.x) {
            x = Math.max(targetSpeed.x, speed.x - acceleration);
        } else {
            x = targetSpeed.x;
        }
        if (targetSpeed.y > speed.y) {
            y = Math.min(targetSpeed.y, speed.y + acceleration);
        } else if (targetSpeed.y < speed.y) {
            y = Math.max(targetSpeed.y, speed.y - acceleration);
        } else {
            y = targetSpeed.y;
        }
        if (targetSpeed.z > speed.z) {
            z = Math.min(targetSpeed.z, speed.z + acceleration);
        } else if (targetSpeed.z < speed.z) {
            z = Math.max(targetSpeed.z, speed.z - acceleration);
        } else {
            z = targetSpeed.z;
        }
        this.setDeltaMovement(x, y, z);
    }

    public void setTargetSpeed(Vec3 targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public Vec3 getTargetSpeed() {
        return this.targetSpeed;
    }
}
