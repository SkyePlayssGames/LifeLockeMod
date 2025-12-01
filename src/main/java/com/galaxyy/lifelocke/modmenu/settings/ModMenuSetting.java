package com.galaxyy.lifelocke.modmenu.settings;

public abstract class ModMenuSetting {
    public ModMenuSetting(String input) { };
    public abstract String to_string();

    public Boolean get_boolean() {
        return null;
    };
    public Integer get_int() {
        return null;
    };
}
