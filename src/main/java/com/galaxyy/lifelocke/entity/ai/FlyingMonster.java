package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public abstract class FlyingMonster extends Monster implements FlyingAnimal {
    @Override
    public boolean isFlying() {
        return true;
    }

    protected FlyingMonster(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
    }

    @Override
    public boolean onGround() {
        return false;
    }

    @Override
    public @NonNull PathNavigation createNavigation(@NonNull Level level) {
        NewNavi navi = new NewNavi(this, level);
        navi.setCanFloat(true);
        navi.setCanOpenDoors(false);
        return navi;
    }

    static class NewNavi extends FlyingPathNavigation {
        public NewNavi(PathfinderMob mob, Level level) {
            super(mob, level);
        }

        @Override
        public void tick() {
            Path path = this.path;
            super.tick();
            this.path = path;
        }
    }
}
