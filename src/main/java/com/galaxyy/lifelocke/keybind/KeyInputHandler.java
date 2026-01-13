package com.galaxyy.lifelocke.keybind;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.ModMenuSetting;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyMapping.Category KEY_CATEGORY_LIFELOCKE = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "category")
    );
    public static final String KEYBIND_ABILITY = "key.lifelocke.ability";

    public static KeyMapping abilityKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (abilityKey.consumeClick()) {
                Vec3 pos = client.hitResult.getLocation();
                BlockPos blockPos = BlockPos.containing(pos.x, pos.y, pos.z);

                SettingsFileHandler.create();
                ModMenuSetting[] settings = SettingsFileHandler.try_read(null);
                SoundEvent toggledSoundEvent = settings[SettingsFileHandler.SETTINGS.POWER_SOUND_TOGGLE.ordinal()].get_powerSound();
                SoundEvent activatedSoundEvent = settings[SettingsFileHandler.SETTINGS.POWER_SOUND_ACTIVE.ordinal()].get_powerSound();

                PressedAbilityKeyC2SPayload payload = new PressedAbilityKeyC2SPayload(blockPos, toggledSoundEvent, activatedSoundEvent);
                ClientPlayNetworking.send(payload);
            }
        });
    }

    public static void registerKeyBindings() {
        abilityKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEYBIND_ABILITY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_LIFELOCKE
        ));

        registerKeyInputs();
    }

}
