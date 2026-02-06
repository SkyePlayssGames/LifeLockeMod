package com.galaxyy.lifelocke.rendering;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
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

import java.util.Objects;

public class TypeIconRenderer {
    private static final Identifier NONE_ID = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/no_effect.png");

    private static void drawTypeIcon(Identifier ID, GuiGraphics context, int x, int y) {
        context.blit(RenderPipelines.GUI_TEXTURED, ID, x, y, 0, 0, 18, 18, 18, 18);
    }

    public static void render(GuiGraphics context, DeltaTracker tickCounter) {
        SettingsFileHandler.create();

        HumanoidArm mainHand = Minecraft.getInstance().options.mainHand().get();
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        Identifier icon = Identifier.parse(((iEntityDataSaver) player).lifelocke$getPersistentData().getStringOr("toggled_power", "lifelocke:null"));

        final Window window = Minecraft.getInstance().getWindow();
        final int Y_COORDINATE  = (int) (((double) window.getGuiScaledHeight()) / 20.0 * 18.35);
        final int X_COORDINATE_RIGHT = (int) (((double) window.getGuiScaledWidth()) / 20.0 * 14.3);
        final int X_COORDINATE_LEFT = (int) (((double) window.getGuiScaledWidth()) / 20.0 * 4.8);

        Identifier iconToShow = Objects.equals(icon, Identifier.fromNamespaceAndPath("lifelocke", "null")) ? NONE_ID :
                Identifier.fromNamespaceAndPath(icon.getNamespace(), "textures/mob_effect/" + icon.getPath() + ".png");

        if (!((iEntityDataSaver) player).lifelocke$getPersistentData().getBooleanOr("server_has_mod", false)
        || !SettingsFileHandler.try_read(null)[SettingsFileHandler.SETTINGS.SHOW_TYPE_ICON.ordinal()].get_boolean()) {
            return;
        }

        if (mainHand == HumanoidArm.RIGHT) {
            drawTypeIcon(iconToShow, context, X_COORDINATE_RIGHT, Y_COORDINATE);
        } else {
            drawTypeIcon(iconToShow, context, X_COORDINATE_LEFT, Y_COORDINATE);
        }
        if (iconToShow != NONE_ID && !player.hasEffect(Types.getType(icon).type)) {
            ((iEntityDataSaver) player).lifelocke$getPersistentData().putString("toggled_power", "lifelocke:null");
        }
    }
}
