package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SoundSettingsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SoundButtonPressAction implements ButtonWidget.PressAction {
    private final MinecraftClient client;
    private final Screen screen;

    public SoundButtonPressAction(MinecraftClient client, Screen screen) {
        this.client = client;
        this.screen = screen;
    }

    @Override
    public void onPress(ButtonWidget button) {
        client.setScreen(new SoundSettingsScreen(Text.literal("Sound Settings"), screen));
    }
}
