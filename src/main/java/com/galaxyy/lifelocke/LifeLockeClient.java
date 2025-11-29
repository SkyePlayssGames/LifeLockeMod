package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.keybind.KeyInputHandler;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SPayload;
import com.galaxyy.lifelocke.networking.RenderTypeIconS2CHandler;
import com.galaxyy.lifelocke.networking.RenderTypeIconS2CPayload;
import com.galaxyy.lifelocke.rendering.TypeIconRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.util.Identifier;

public class LifeLockeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.registerKeyBindings();
        ClientPlayNetworking.registerGlobalReceiver(RenderTypeIconS2CPayload.ID, new RenderTypeIconS2CHandler());

        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
                Identifier.of(LifeLocke.MOD_ID, "type_icon"), TypeIconRenderer::render);
    }
}
