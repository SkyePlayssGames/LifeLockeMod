package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SoundSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SoundButtonPressAction implements Button.OnPress {
    private final Minecraft client;
    private final Screen screen;

    public SoundButtonPressAction(Minecraft client, Screen screen) {
        this.client = client;
        this.screen = screen;
    }

    @Override
    public void onPress(Button button) {
        client.setScreen(new SoundSettingsScreen(Component.literal("Sound Settings"), screen));
    }
}
