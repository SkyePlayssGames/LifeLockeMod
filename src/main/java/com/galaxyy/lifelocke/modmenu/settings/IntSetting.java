package com.galaxyy.lifelocke.modmenu.settings;

import java.util.Objects;

public class IntSetting extends ModMenuSetting {
    int value;

    public IntSetting(String input) {
        super(input);
        value = Integer.parseInt(input);
    }

    @Override
    public String to_string() {
        return String.valueOf(value);
    }

    @Override
    public Integer get_int() {
        return value;
    }
}
