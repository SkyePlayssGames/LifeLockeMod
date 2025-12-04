package com.galaxyy.lifelocke.modmenu;

import com.galaxyy.lifelocke.modmenu.buttons.NullIconButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.PowerDefaultButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.SoundButtonPressAction;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ModConfigScreen extends Screen {
    private final Screen parent;

    @Override
    public void close() {
        client.setScreen(parent);
    }

    protected ModConfigScreen(Text title, Screen screen) {
        super(title);
        this.parent = screen;
    }

    @Override
    protected void init() {
        ButtonWidget nullIconButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.null_icon.button"),
                new NullIconButtonPressAction(this.client)
        ).dimensions(this.width/2-120, 40, 240, 20).build();

        ButtonWidget powerDefaultButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.power_default.button"),
                new PowerDefaultButtonPressAction(this.client)
        ).dimensions(this.width/2-120, 65, 240, 20).build();

        ButtonWidget soundButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.sound.button"),
                new SoundButtonPressAction(this.client, this)
        ).dimensions(this.width/2-120, 90, 240, 20).build();

        ButtonWidget closeButton = ButtonWidget.builder(Text.translatable("text.lifelocke.modmenu.close.button"), (button -> {
            this.close();
        })).dimensions(this.width/2-120, this.height-50, 240, 20).build();


        this.addDrawable(nullIconButton);
        this.addSelectableChild(nullIconButton);
        this.addDrawable(powerDefaultButton);
        this.addSelectableChild(powerDefaultButton);
        this.addDrawable(soundButton);
        this.addSelectableChild(soundButton);
        this.addDrawable(closeButton);
        this.addSelectableChild(closeButton);
    }
}
