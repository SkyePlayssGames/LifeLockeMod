package com.galaxyy.lifelocke.rendering;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;

public class TypeIconRenderer {
    private static final Identifier ELECTRIC_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/mob_effect/electric.png");
    private static final Identifier ICE_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/mob_effect/ice.png");
    private static final Identifier POISON_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/mob_effect/poison.png");
    private static final Identifier DARK_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/mob_effect/dark.png");
    private static final Identifier PSYCHIC_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/mob_effect/psychic.png");
    private static final Identifier GHOST_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/mob_effect/ghost.png");
    private static final Identifier NONE_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/no_effect.png");

    private static void drawTypeIcon(Identifier ID, GuiGraphics context, int x, int y) {
        context.blit(RenderPipelines.GUI_TEXTURED, ID, x, y, 0, 0, 18, 18, 18, 18);
    }

    public static void render(GuiGraphics context, DeltaTracker tickCounter) {
        SettingsFileHandler.create();

        HumanoidArm mainHand = Minecraft.getInstance().options.mainHand().get();
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        int icon = ((iEntityDataSaver) player).lifelocke$getPersistentData().getIntOr("type_icon", 0);

        final Window window = Minecraft.getInstance().getWindow();
        final int Y_COORDINATE  = (int) (((double) window.getGuiScaledHeight()) / 20.0 * 18.35);
        final int X_COORDINATE_RIGHT = (int) (((double) window.getGuiScaledWidth()) / 20.0 * 14.3);
        final int X_COORDINATE_LEFT = (int) (((double) window.getGuiScaledWidth()) / 20.0 * 4.8);

        boolean showed_icon = false;

        if (!((iEntityDataSaver) player).lifelocke$getPersistentData().getBooleanOr("server_has_mod", false)
        || !SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.SHOW_TYPE_ICON.ordinal()].get_boolean()) {
            return;
        }

        if (mainHand == HumanoidArm.RIGHT) { switch (icon) {
            case 1:
                if (player.hasEffect(ModEffects.ELECTRIC)) {
                    drawTypeIcon(ELECTRIC_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 2:
                if (player.hasEffect(ModEffects.ICE)) {
                    drawTypeIcon(ICE_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 3:
                if (player.hasEffect(ModEffects.POISON)) {
                    drawTypeIcon(POISON_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 4:
                if (player.hasEffect(ModEffects.DARK)) {
                    drawTypeIcon(DARK_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 5:
                if (player.hasEffect(ModEffects.PSYCHIC)) {
                    drawTypeIcon(PSYCHIC_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 6:
                if (player.hasEffect(ModEffects.GHOST)) {
                    drawTypeIcon(GHOST_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            default:
                drawTypeIcon(NONE_ID, context, X_COORDINATE_RIGHT, Y_COORDINATE);
                showed_icon = true;
                break;
        }} else { switch (icon) {
            case 1:
                if (player.hasEffect(ModEffects.ELECTRIC)) {
                    drawTypeIcon(ELECTRIC_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 2:
                if (player.hasEffect(ModEffects.ICE)) {
                    drawTypeIcon(ICE_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 3:
                if (player.hasEffect(ModEffects.POISON)) {
                    drawTypeIcon(POISON_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 4:
                if (player.hasEffect(ModEffects.DARK)) {
                    drawTypeIcon(DARK_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 5:
                if (player.hasEffect(ModEffects.PSYCHIC)) {
                    drawTypeIcon(PSYCHIC_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            case 6:
                if (player.hasEffect(ModEffects.GHOST)) {
                    drawTypeIcon(GHOST_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                    showed_icon = true;
                }
                break;
            default:
                drawTypeIcon(NONE_ID, context, X_COORDINATE_LEFT, Y_COORDINATE);
                showed_icon = true;
                break;
        }}
        if (!showed_icon) {
            ((iEntityDataSaver) player).lifelocke$getPersistentData().putInt("type_icon", 0);
        }
    }
}
