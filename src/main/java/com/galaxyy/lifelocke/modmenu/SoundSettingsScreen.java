package com.galaxyy.lifelocke.modmenu;

import com.galaxyy.lifelocke.modmenu.buttons.NullIconButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.PowerDefaultButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.SoundButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.SoundSwitchButtonPressAction;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SoundSettingsScreen extends Screen {
    private final Screen parent;

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }

    public SoundSettingsScreen(Component title, Screen screen) {
        super(title);
        this.parent = screen;
    }

    @Override
    protected void init() {
        Button toggleSwitchButton = Button.builder(Component.translatable("text.lifelocke.modmenu.toggle_switch.button"),
                new SoundSwitchButtonPressAction(this.minecraft, true)
        ).bounds(this.width/2-120, 40, 240, 20).build();

        Button activeSwitchButton = Button.builder(Component.translatable("text.lifelocke.modmenu.active_switch.button"),
                new SoundSwitchButtonPressAction(this.minecraft, false)
        ).bounds(this.width/2-120, 65, 240, 20).build();

        Button closeButton = Button.builder(Component.translatable("text.lifelocke.modmenu.close.button"), (button -> {
            this.onClose();
        })).bounds(this.width/2-120, this.height-50, 240, 20).build();


        this.addRenderableOnly(toggleSwitchButton);
        this.addWidget(toggleSwitchButton);
        this.addRenderableOnly(activeSwitchButton);
        this.addWidget(activeSwitchButton);
        this.addRenderableOnly(closeButton);
        this.addWidget(closeButton);
    }
}
