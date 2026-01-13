package com.galaxyy.lifelocke.modmenu;

import com.galaxyy.lifelocke.modmenu.buttons.NullIconButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.PowerDefaultButtonPressAction;
import com.galaxyy.lifelocke.modmenu.buttons.SoundButtonPressAction;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModConfigScreen extends Screen {
    private final Screen parent;

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }

    protected ModConfigScreen(Component title, Screen screen) {
        super(title);
        this.parent = screen;
    }

    @Override
    protected void init() {
        Button nullIconButton = Button.builder(Component.translatable("text.lifelocke.modmenu.null_icon.button"),
                new NullIconButtonPressAction(this.minecraft)
        ).bounds(this.width/2-120, 40, 240, 20).build();

        Button powerDefaultButton = Button.builder(Component.translatable("text.lifelocke.modmenu.power_default.button"),
                new PowerDefaultButtonPressAction(this.minecraft)
        ).bounds(this.width/2-120, 65, 240, 20).build();

        Button soundButton = Button.builder(Component.translatable("text.lifelocke.modmenu.sound.button"),
                new SoundButtonPressAction(this.minecraft, this)
        ).bounds(this.width/2-120, 90, 240, 20).build();

        Button closeButton = Button.builder(Component.translatable("text.lifelocke.modmenu.close.button"), (button -> {
            this.onClose();
        })).bounds(this.width/2-120, this.height-50, 240, 20).build();

        StringWidget creditsText = new StringWidget(this.width/2-120, this.height-20, 240, 10, Component.translatable("text.lifelocke.credits"), font);


        this.addRenderableOnly(nullIconButton);
        this.addWidget(nullIconButton);
        this.addRenderableOnly(powerDefaultButton);
        this.addWidget(powerDefaultButton);
        this.addRenderableOnly(soundButton);
        this.addWidget(soundButton);
        this.addRenderableOnly(closeButton);
        this.addWidget(closeButton);
        this.addRenderableOnly(creditsText);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        context.drawString(minecraft.font, "Nyaa", this.width/2, this.height/4*3, 0xffffff, true);
    }
}
