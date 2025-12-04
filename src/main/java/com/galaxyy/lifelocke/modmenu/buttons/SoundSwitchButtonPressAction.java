package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.IntSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import com.galaxyy.lifelocke.modmenu.settings.PowerSoundSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.AbstractBeeSoundInstance;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class SoundSwitchButtonPressAction implements ButtonWidget.PressAction {
    private final MinecraftClient client;
    private final int SETTINGS_LINE;
    private final int SETTINGS_ORDER;

    public SoundSwitchButtonPressAction(MinecraftClient client, int setting, int setting_order) {
        this.client = client;
        this.SETTINGS_LINE = setting;
        this.SETTINGS_ORDER = setting_order;
    }

    @Override
    public void onPress(ButtonWidget button) {
        SettingsFileHandler.create();
        ModMenuSetting[] settings = SettingsFileHandler.try_read(null);

        IntSetting order = (IntSetting) settings[SETTINGS_ORDER];
        PowerSoundSetting sound = PowerSoundSetting.cycle(order.get_int());
        order.increment().overflow(PowerSoundSetting.amountOfSounds - 1, 0);

        settings[SETTINGS_LINE] = sound;
        settings[SETTINGS_ORDER] = order;
        SettingsFileHandler.try_write(settings);

        this.client.getToastManager().add(
                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                        Text.translatable("text.lifelocke.modmenu.updated_settings"),
                        Text.translatable("text.lifelocke.modmenu.set_setting.set_sound.description",
                                sound.to_string()
                        )
                )
        );
    }
}
