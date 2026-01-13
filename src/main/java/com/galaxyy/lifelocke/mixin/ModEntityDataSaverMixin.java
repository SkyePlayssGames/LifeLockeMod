package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements iEntityDataSaver {
    @Shadow @Final private EntityType<?> type;
    @Unique
    private CompoundTag persistentData;

    @Override
    public CompoundTag lifelocke$getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new CompoundTag();
        }

        return persistentData;
    }

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void injectWriteMethod(ValueOutput view, CallbackInfo ci) {
        if (persistentData != null) {
            view.store("lifelocke.data", CompoundTag.CODEC, persistentData);
        }
    }

    @Inject(method = "load", at = @At("HEAD"))
    protected void injectReadMethod(ValueInput view, CallbackInfo ci) {
        if (view.contains("lifelocke.data")) {
            persistentData = view.read("lifelocke.data", CompoundTag.CODEC).orElse(new CompoundTag());
        }
    }
}
