package com.galaxyy.lifelocke.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class GrassMobEntity extends HostileEntity {
    public GrassMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
}
