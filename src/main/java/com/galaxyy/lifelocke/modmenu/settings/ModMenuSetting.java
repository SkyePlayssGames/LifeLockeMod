package com.galaxyy.lifelocke.modmenu.settings;

import net.minecraft.sound.SoundEvent;

public abstract class ModMenuSetting {
    public ModMenuSetting(String input) { };
    public abstract String to_string();

    public Boolean get_boolean() {
        return null;
    };
    public Integer get_int() {
        return null;
    };
    public SoundEvent get_powerSound() {
        return null;
    }
}
