package com.galaxyy.lifelocke.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModConfigScreenFactory implements ConfigScreenFactory<ModConfigScreen> {
    @Override
    public ModConfigScreen create(Screen screen) {
        return new ModConfigScreen(Component.literal("LifeLocke Settings"), screen);
    }
}