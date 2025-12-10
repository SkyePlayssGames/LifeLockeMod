package com.galaxyy.lifelocke.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreenFactory implements ConfigScreenFactory<ModConfigScreen> {
    @Override
    public ModConfigScreen create(Screen screen) {
        return new ModConfigScreen(Text.literal("LifeLocke Settings"), screen);
    }
}