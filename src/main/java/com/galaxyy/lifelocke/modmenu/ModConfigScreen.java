package com.galaxyy.lifelocke.modmenu;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ModConfigScreen extends Screen {


    protected ModConfigScreen(Text title, Screen screen) {
        super(title);
    }

    @Override
    protected void init() {
        ButtonWidget testButton = ButtonWidget.builder(Text.literal("Toggle Null Icon Showing"), (button -> {
            SettingsFileHandler.create();
            String[] settings = SettingsFileHandler.read();
            if (Objects.equals(settings[0], "0")) {
                settings[0] = "1";
            } else {
                settings[0] = "0";
            }
            SettingsFileHandler.write(settings);
            if (Objects.equals(settings[0], "0")) {
                this.client.getToastManager().add(
                        SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.translatable("text.lifelocke.modmenu.set_setting.null_icon.title"), Text.translatable("text.lifelocke.modmenu.set_setting.null_icon.description", Text.translatable("text.lifelocke.false")))
                );
            } else {
                this.client.getToastManager().add(
                        SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.translatable("text.lifelocke.modmenu.set_setting.null_icon.title"), Text.translatable("text.lifelocke.modmenu.set_setting.null_icon.description", Text.translatable("text.lifelocke.true")))
                );
            }
        })).dimensions(this.width/2-120, 40, 240, 20).build();

        ButtonWidget closeButton = ButtonWidget.builder(Text.literal("Close"), (button -> {
            this.close();
        })).dimensions(this.width/2-120, this.height-50, 240, 20).build();

        this.addDrawable(testButton);
        this.addSelectableChild(testButton);
        this.addDrawable(closeButton);
        this.addSelectableChild(closeButton);
    }
}
