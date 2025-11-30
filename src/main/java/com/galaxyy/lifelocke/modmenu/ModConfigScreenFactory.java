package com.galaxyy.lifelocke.modmenu;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreenFactory implements com.terraformersmc.modmenu.api.ConfigScreenFactory {
    @Override
    public Screen create(Screen screen) {
        return new ModConfigScreen(Text.literal("LifeLocke Settings"), screen);
    }
}
