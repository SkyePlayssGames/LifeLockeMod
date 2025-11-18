package com.galaxyy.lifelocke.keybind;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyBinding.Category KEY_CATEGORY_LIFELOCKE = new KeyBinding.Category(
            Identifier.of(LifeLocke.MOD_ID, "category")
    );
    public static final String KEYBIND_ABILITY = "key.lifelocke.ability";

    public static KeyBinding abilityKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (abilityKey.wasPressed()) {
                Vec3d pos = client.crosshairTarget.getPos();
                BlockPos blockPos = BlockPos.ofFloored(pos.x, pos.y, pos.z);
                PressedAbilityKeyC2SPayload payload = new PressedAbilityKeyC2SPayload(blockPos);
                ClientPlayNetworking.send(payload);
            }
        });
    }

    public static void registerKeyBindings() {
        abilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEYBIND_ABILITY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_LIFELOCKE
        ));

        registerKeyInputs();
    }

}
