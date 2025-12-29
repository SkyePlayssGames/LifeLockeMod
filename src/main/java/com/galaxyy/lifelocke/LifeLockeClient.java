package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.entity.ModEntities;
import com.galaxyy.lifelocke.entity.client.fire_mob.FireMobModel;
import com.galaxyy.lifelocke.entity.client.fire_mob.FireMobRenderer;
import com.galaxyy.lifelocke.entity.client.grass_mob.GrassMobModel;
import com.galaxyy.lifelocke.entity.client.grass_mob.GrassMobRenderer;
import com.galaxyy.lifelocke.event.ModCheckExistingEvent;
import com.galaxyy.lifelocke.keybind.KeyInputHandler;
import com.galaxyy.lifelocke.networking.*;
import com.galaxyy.lifelocke.rendering.TypeIconRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.impl.client.rendering.EntityRendererRegistryImpl;
import net.minecraft.util.Identifier;

public class LifeLockeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.registerKeyBindings();
        ClientPlayNetworking.registerGlobalReceiver(RenderTypeIconS2CPayload.ID, new RenderTypeIconS2CHandler());
        ClientPlayNetworking.registerGlobalReceiver(ServerModCheckS2CPayload.ID, new ServerModCheckS2CHandler());

        ClientPlayConnectionEvents.JOIN.register(new ModCheckExistingEvent());

        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
                Identifier.of(LifeLocke.MOD_ID, "type_icon"), TypeIconRenderer::render);

        EntityModelLayerRegistry.registerModelLayer(FireMobModel.FIRE_MOB, FireMobModel::getTexturedModelData);
        EntityRendererRegistryImpl.register(ModEntities.FIRE_MOB, FireMobRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(GrassMobModel.GRASS_MOB, GrassMobModel::getTexturedModelData);
        EntityRendererRegistryImpl.register(ModEntities.GRASS_MOB, GrassMobRenderer::new);
    }
}
