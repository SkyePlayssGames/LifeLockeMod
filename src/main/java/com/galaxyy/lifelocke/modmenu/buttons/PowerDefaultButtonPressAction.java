package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import java.util.Objects;

public class PowerDefaultButtonPressAction implements ButtonWidget.PressAction {
    private final MinecraftClient client;
    private static final int SETTINGS_LINE = SettingsFileHandler.SETTINGS.POWER_DEFAULT.ordinal();

    public PowerDefaultButtonPressAction(MinecraftClient client) {
        this.client = client;
    }

    @Override
    public void onPress(ButtonWidget button) {
        SettingsFileHandler.create();
        ModMenuSetting[] settings = SettingsFileHandler.try_read(null);
        Boolean powerDefault = settings[SETTINGS_LINE].get_boolean();
        if (powerDefault) {
            settings[SETTINGS_LINE] = new BooleanSetting("F");
        } else {
            settings[SETTINGS_LINE] = new BooleanSetting("T");
        }
        powerDefault = !powerDefault;
        SettingsFileHandler.try_write(settings);
        if (powerDefault) {
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                            Text.translatable("text.lifelocke.modmenu.updated_settings"),
                            Text.translatable("text.lifelocke.modmenu.set_setting.power_default.description",
                                    Text.translatable("text.lifelocke.true")
                            )
                    )
            );
        } else {
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                            Text.translatable("text.lifelocke.modmenu.updated_settings"),
                            Text.translatable("text.lifelocke.modmenu.set_setting.power_default.description",
                                    Text.translatable("text.lifelocke.false")
                            )
                    )
            );
        }
    }
}
