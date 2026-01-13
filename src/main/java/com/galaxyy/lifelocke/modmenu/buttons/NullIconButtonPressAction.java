package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class NullIconButtonPressAction implements Button.OnPress {
    private final Minecraft client;
    private static final int SETTINGS_LINE = SettingsFileHandler.SETTINGS.SHOW_TYPE_ICON.ordinal();

    public NullIconButtonPressAction(Minecraft client) {
        this.client = client;
    }

    @Override
    public void onPress(Button button) {
        SettingsFileHandler.create();
        ModMenuSetting[] settings = SettingsFileHandler.try_read(null);
        Boolean null_icon = settings[SETTINGS_LINE].get_boolean();
        if (null_icon) {
            settings[SETTINGS_LINE] = new BooleanSetting("F");
        } else {
            settings[SETTINGS_LINE] = new BooleanSetting("T");
        }
        null_icon = !null_icon;
        SettingsFileHandler.try_write(settings);
        if (null_icon) {
            this.client.getToastManager().addToast(
                    SystemToast.multiline(this.client, SystemToast.SystemToastId.NARRATOR_TOGGLE,
                            Component.translatable("text.lifelocke.modmenu.updated_settings"),
                            Component.translatable("text.lifelocke.modmenu.set_setting.null_icon.description",
                                    Component.translatable("text.lifelocke.true")
                            )
                    )
            );
        } else {
            this.client.getToastManager().addToast(
                    SystemToast.multiline(this.client, SystemToast.SystemToastId.NARRATOR_TOGGLE,
                            Component.translatable("text.lifelocke.modmenu.updated_settings"),
                            Component.translatable("text.lifelocke.modmenu.set_setting.null_icon.description",
                                    Component.translatable("text.lifelocke.false")
                            )
                    )
            );
        }
    }
}
