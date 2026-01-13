package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.core.Vec3i;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public record PressedAbilityKeyC2SPayload(Vec3i hitPos, SoundEvent toggledSoundEvent, SoundEvent activatedSoundEvent)
        implements CustomPacketPayload {
    public static final Identifier PRESSED_ABILITY_KEY = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "pressed_ability_key_packet");
    public static final CustomPacketPayload.Type<PressedAbilityKeyC2SPayload> ID = new CustomPacketPayload.Type<>(PRESSED_ABILITY_KEY);
    public static final StreamCodec<RegistryFriendlyByteBuf, PressedAbilityKeyC2SPayload> CODEC = StreamCodec.composite(
            Vec3i.STREAM_CODEC, PressedAbilityKeyC2SPayload::hitPos,
            SoundEvent.DIRECT_STREAM_CODEC, PressedAbilityKeyC2SPayload::toggledSoundEvent,
            SoundEvent.DIRECT_STREAM_CODEC, PressedAbilityKeyC2SPayload::activatedSoundEvent,
            PressedAbilityKeyC2SPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
