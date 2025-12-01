package com.galaxyy.lifelocke.modmenu.settings;

import java.util.Objects;

public class BooleanSetting extends ModMenuSetting {
    Boolean value;

    public BooleanSetting(String input) {
        super(input);
        value = Objects.equals(input, "T");
    }

    @Override
    public String to_string() {
        if (value) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Boolean get_boolean() {
        return value;
    }
}
