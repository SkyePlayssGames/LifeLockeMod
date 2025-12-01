package com.galaxyy.lifelocke.rendering;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class TypeIconRenderer {
    private static final Identifier ELECTRIC_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/electric.png");
    private static final Identifier ICE_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/ice.png");
    private static final Identifier POISON_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/poison.png");
    private static final Identifier DARK_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/dark.png");
    private static final Identifier PSYCHIC_ID = Identifier.of(LifeLocke.MOD_ID, "textures/mob_effect/psychic.png");
    private static final Identifier NONE_ID = Identifier.of(LifeLocke.MOD_ID, "textures/no_effect.png");

    private static final int X_COORDINATE_RIGHT = 333;
    private static final int X_COORDINATE_LEFT = 128;
    private static final int Y_COORDINATE = 227;

    public static void render(DrawContext context, RenderTickCounter tickCounter) {
        SettingsFileHandler.create();

        int mainHand = MinecraftClient.getInstance().options.getMainArm().getValue().getId();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        int icon = ((iEntityDataSaver) player).lifelocke$getPersistentData().getInt("type_icon", 0);

        if (mainHand == 1) { switch (icon) {
            case 1:
                if (player.hasStatusEffect(ModEffects.ELECTRIC)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, ELECTRIC_ID, X_COORDINATE_RIGHT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 2:
                if (player.hasStatusEffect(ModEffects.ICE)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, ICE_ID, X_COORDINATE_RIGHT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 3:
                if (player.hasStatusEffect(ModEffects.POISON)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, POISON_ID, X_COORDINATE_RIGHT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 4:
                if (player.hasStatusEffect(ModEffects.DARK)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, DARK_ID, X_COORDINATE_RIGHT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 5:
                if (player.hasStatusEffect(ModEffects.PSYCHIC)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, PSYCHIC_ID, X_COORDINATE_RIGHT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            default:
                if (SettingsFileHandler.read()[SettingsFileHandler.SETTINGS.NULL_ICON.ordinal()].get_boolean()) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, NONE_ID, X_COORDINATE_RIGHT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
        }} else { switch (icon) {
            case 1:
                if (player.hasStatusEffect(ModEffects.ELECTRIC)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, ELECTRIC_ID, X_COORDINATE_LEFT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 2:
                if (player.hasStatusEffect(ModEffects.ICE)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, ICE_ID, X_COORDINATE_LEFT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 3:
                if (player.hasStatusEffect(ModEffects.POISON)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, POISON_ID, X_COORDINATE_LEFT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 4:
                if (player.hasStatusEffect(ModEffects.DARK)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, DARK_ID, X_COORDINATE_LEFT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            case 5:
                if (player.hasStatusEffect(ModEffects.PSYCHIC)) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, PSYCHIC_ID, X_COORDINATE_LEFT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
            default:
                if (SettingsFileHandler.read()[SettingsFileHandler.SETTINGS.NULL_ICON.ordinal()].get_boolean()) {
                    context.drawTexture(RenderPipelines.GUI_TEXTURED, NONE_ID, X_COORDINATE_LEFT, Y_COORDINATE, 0, 0, 18, 18, 18, 18);
                }
                break;
        }}
    }
}
