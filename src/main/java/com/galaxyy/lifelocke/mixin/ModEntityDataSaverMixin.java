package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
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
    private NbtCompound persistentData;

    @Override
    public NbtCompound lifelocke$getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }

        return persistentData;
    }

    @Inject(method = "writeData", at = @At("HEAD"))
    protected void injectWriteMethod(WriteView view, CallbackInfo ci) {
        if (persistentData != null) {
            view.put("lifelocke.data", NbtCompound.CODEC, persistentData);
        }
    }

    @Inject(method = "readData", at = @At("HEAD"))
    protected void injectReadMethod(ReadView view, CallbackInfo ci) {
        if (view.contains("lifelocke.data")) {
            persistentData = view.read("lifelocke.data", NbtCompound.CODEC).orElse(new NbtCompound());
        }
    }
}
