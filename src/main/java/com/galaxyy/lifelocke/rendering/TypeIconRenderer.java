package com.galaxyy.lifelocke.rendering;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class TypeIconRenderer {
    private static final Identifier ELECTRIC_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/electric.png");
    private static final Identifier ICE_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/ice.png");
    private static final Identifier POISON_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/poison.png");
    private static final Identifier DARK_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/dark.png");
    private static final Identifier PSYCHIC_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/psychic.png");

    public static void render(DrawContext context, RenderTickCounter tickCounter) {
        int icon = ((iEntityDataSaver) MinecraftClient.getInstance().player).lifelocke$getPersistentData().getInt("type_icon", 0);
        switch (icon) {
            case 1:
                context.drawTexture(RenderPipelines.GUI_TEXTURED, ELECTRIC_ID, 128, 227, 0, 0, 18, 18, 18, 18);
                break;
            case 2:
                context.drawTexture(RenderPipelines.GUI_TEXTURED, ICE_ID, 128, 227, 0, 0, 18, 18, 18, 18);
                break;
            case 3:
                context.drawTexture(RenderPipelines.GUI_TEXTURED, POISON_ID, 128, 227, 0, 0, 18, 18, 18, 18);
                break;
            case 4:
                context.drawTexture(RenderPipelines.GUI_TEXTURED, DARK_ID, 128, 227, 0, 0, 18, 18, 18, 18);
                break;
            case 5:
                context.drawTexture(RenderPipelines.GUI_TEXTURED, PSYCHIC_ID, 128, 227, 0, 0, 18, 18, 18, 18);
                break;
        }
    }
}
