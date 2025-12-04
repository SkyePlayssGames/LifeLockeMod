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

    public IntSetting increment() {
        value += 1;
        return this;
    }

    public IntSetting clamp(int min, int max) {
        if (value > max) {
            value = max;
        } else if (value < min) {
            value = min;
        }
        return this;
    }

    public IntSetting overflow(int max, int min) {
        if (value > max) {
            value = min;
        }
        return this;
    }

    public IntSetting underflow(int min, int max) {
        if (value > min) {
            value = max;
        }
        return this;
    }
}
