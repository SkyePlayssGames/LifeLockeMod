package com.galaxyy.lifelocke.modmenu;

import com.galaxyy.lifelocke.modmenu.buttons.NullIconButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.PowerDefaultButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.SoundButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.SoundSwitchButtonPressAction;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SoundSettingsScreen extends Screen {
    private final Screen parent;

    @Override
    public void close() {
        client.setScreen(parent);
    }

    public SoundSettingsScreen(Text title, Screen screen) {
        super(title);
        this.parent = screen;
    }

    @Override
    protected void init() {
        ButtonWidget toggleSwitchButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.toggle_switch.button"),
                new SoundSwitchButtonPressAction(this.client, SettingsFileHandler.SETTINGS.POWER_SOUND_TOGGLE.ordinal(), SettingsFileHandler.SETTINGS.POWER_SOUND_TOGGLE_ORDER.ordinal())
        ).dimensions(this.width/2-120, 40, 240, 20).build();

        ButtonWidget activeSwitchButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.active_switch.button"),
                new SoundSwitchButtonPressAction(this.client, SettingsFileHandler.SETTINGS.POWER_SOUND_ACTIVE.ordinal(), SettingsFileHandler.SETTINGS.POWER_SOUND_ACTIVE_ORDER.ordinal())
        ).dimensions(this.width/2-120, 65, 240, 20).build();

        ButtonWidget closeButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.close.button"), (button -> {
            this.close();
        })).dimensions(this.width/2-120, this.height-50, 240, 20).build();


        this.addDrawable(toggleSwitchButton);
        this.addSelectableChild(toggleSwitchButton);
        this.addDrawable(activeSwitchButton);
        this.addSelectableChild(activeSwitchButton);
        this.addDrawable(closeButton);
        this.addSelectableChild(closeButton);
    }
}
