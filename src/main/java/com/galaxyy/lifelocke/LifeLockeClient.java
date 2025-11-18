package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.keybind.KeyInputHandler;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class LifeLockeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.registerKeyBindings();
    }
}
