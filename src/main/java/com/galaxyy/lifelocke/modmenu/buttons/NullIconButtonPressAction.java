package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import java.util.Objects;

public class NullIconButtonPressAction implements ButtonWidget.PressAction {
    private final MinecraftClient client;
    private static final int SETTINGS_LINE = SettingsFileHandler.SETTINGS.NULL_ICON.ordinal();

    public NullIconButtonPressAction(MinecraftClient client) {
        this.client = client;
    }

    @Override
    public void onPress(ButtonWidget button) {
        SettingsFileHandler.create();
        ModMenuSetting[] settings = SettingsFileHandler.read();
        Boolean null_icon = settings[SETTINGS_LINE].get_boolean();
        if (null_icon) {
            settings[SETTINGS_LINE] = new BooleanSetting("F");
        } else {
            settings[SETTINGS_LINE] = new BooleanSetting("T");
        }
        null_icon = !null_icon;
        SettingsFileHandler.write(settings);
        if (null_icon) {
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                            Text.translatable("text.lifelocke.modmenu.updated_settings"),
                            Text.translatable("text.lifelocke.modmenu.set_setting.null_icon.description",
                                    Text.translatable("text.lifelocke.true")
                            )
                    )
            );
        } else {
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                            Text.translatable("text.lifelocke.modmenu.updated_settings"),
                            Text.translatable("text.lifelocke.modmenu.set_setting.null_icon.description",
                                    Text.translatable("text.lifelocke.false")
                            )
                    )
            );
        }
    }
}
