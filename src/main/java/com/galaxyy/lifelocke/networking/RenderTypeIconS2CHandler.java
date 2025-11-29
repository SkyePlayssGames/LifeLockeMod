package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.*;
import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;

public class RenderTypeIconS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<RenderTypeIconS2CPayload> {
    @Override
    public void receive(RenderTypeIconS2CPayload renderTypeIconS2CPayload, ClientPlayNetworking.Context context) {
        ((iEntityDataSaver) context.player()).getPersistentData().putInt("type_icon", renderTypeIconS2CPayload.icon());
    }
}
