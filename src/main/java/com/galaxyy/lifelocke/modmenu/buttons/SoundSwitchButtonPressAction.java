package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.IntSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import com.galaxyy.lifelocke.modmenu.settings.PowerSoundSetting;
import com.galaxyy.lifelocke.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class SoundSwitchButtonPressAction implements Button.OnPress {
    static class ModMenuSoundInstance extends AbstractSoundInstance {
        protected ModMenuSoundInstance(SoundEvent sound) {
            super(sound, SoundSource.UI, SoundInstance.createUnseededRandom());
        }
    }


    private final Minecraft client;
    private final int SETTINGS_LINE;
    private final int SETTINGS_ORDER;
    private final String description_key;
    private final boolean toggled;

    public SoundSwitchButtonPressAction(Minecraft client, boolean toggled) {
        this.client = client;
        this.toggled = toggled;
        if (toggled) {
            this.SETTINGS_LINE = SettingsFileHandler.SETTINGS.POWER_SOUND_TOGGLE.ordinal();
            this.SETTINGS_ORDER = SettingsFileHandler.SETTINGS.POWER_SOUND_TOGGLE_ORDER.ordinal();
            this.description_key = "text.lifelocke.modmenu.set_setting.toggle_switch.description";
        } else {
            this.SETTINGS_LINE = SettingsFileHandler.SETTINGS.POWER_SOUND_ACTIVE.ordinal();
            this.SETTINGS_ORDER = SettingsFileHandler.SETTINGS.POWER_SOUND_ACTIVE_ORDER.ordinal();
            this.description_key = "text.lifelocke.modmenu.set_setting.active_switch.description";
        }
    }

    @Override
    public void onPress(Button button) {
        SettingsFileHandler.create();
        ModMenuSetting[] settings = SettingsFileHandler.try_read(null);

        IntSetting order = (IntSetting) settings[SETTINGS_ORDER];
        PowerSoundSetting sound = PowerSoundSetting.cycle(order.get_int(), toggled);
        order.increment().overflow(PowerSoundSetting.amountOfSounds - 1, 0);

        settings[SETTINGS_LINE] = sound;
        settings[SETTINGS_ORDER] = order;
        SettingsFileHandler.try_write(settings);

        this.client.getToastManager().addToast(
                SystemToast.multiline(this.client, SystemToast.SystemToastId.NARRATOR_TOGGLE,
                        Component.translatable("text.lifelocke.modmenu.updated_settings"),
                        Component.translatable(description_key, sound.to_string().substring(4))
                )
        );

        this.client.getSoundManager().play(new ModMenuSoundInstance(sound.get_powerSound()));
    }
}
