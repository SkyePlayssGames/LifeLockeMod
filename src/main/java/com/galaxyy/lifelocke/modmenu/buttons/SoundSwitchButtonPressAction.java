package com.galaxyy.lifelocke.modmenu.buttons;

import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.BooleanSetting;
import com.galaxyy.lifelocke.modmenu.settings.IntSetting;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import com.galaxyy.lifelocke.modmenu.settings.PowerSoundSetting;
import com.galaxyy.lifelocke.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.AbstractBeeSoundInstance;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class SoundSwitchButtonPressAction implements ButtonWidget.PressAction {
    static class ModMenuSoundInstance extends AbstractSoundInstance {
        protected ModMenuSoundInstance(SoundEvent sound) {
            super(sound, SoundCategory.UI, SoundInstance.createRandom());
        }
    }


    private final MinecraftClient client;
    private final int SETTINGS_LINE;
    private final int SETTINGS_ORDER;
    private final String description_key;
    private final boolean toggled;

    public SoundSwitchButtonPressAction(MinecraftClient client, boolean toggled) {
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
    public void onPress(ButtonWidget button) {
        SettingsFileHandler.create();
        ModMenuSetting[] settings = SettingsFileHandler.try_read(null);

        IntSetting order = (IntSetting) settings[SETTINGS_ORDER];
        PowerSoundSetting sound = PowerSoundSetting.cycle(order.get_int(), toggled);
        order.increment().overflow(PowerSoundSetting.amountOfSounds - 1, 0);

        settings[SETTINGS_LINE] = sound;
        settings[SETTINGS_ORDER] = order;
        SettingsFileHandler.try_write(settings);

        this.client.getToastManager().add(
                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                        Text.translatable("text.lifelocke.modmenu.updated_settings"),
                        Text.translatable(description_key, sound.to_string().substring(4))
                )
        );

        this.client.getSoundManager().play(new ModMenuSoundInstance(sound.get_powerSound()));
    }
}
