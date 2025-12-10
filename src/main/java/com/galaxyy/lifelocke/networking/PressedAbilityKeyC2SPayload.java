package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;

public record PressedAbilityKeyC2SPayload(Vec3i hitPos, SoundEvent toggledSoundEvent, SoundEvent activatedSoundEvent)
        implements CustomPayload {
    public static final Identifier PRESSED_ABILITY_KEY = Identifier.of(LifeLocke.MOD_ID, "pressed_ability_key_packet");
    public static final CustomPayload.Id<PressedAbilityKeyC2SPayload> ID = new CustomPayload.Id<>(PRESSED_ABILITY_KEY);
    public static final PacketCodec<RegistryByteBuf, PressedAbilityKeyC2SPayload> CODEC = PacketCodec.tuple(
            Vec3i.PACKET_CODEC, PressedAbilityKeyC2SPayload::hitPos,
            SoundEvent.PACKET_CODEC, PressedAbilityKeyC2SPayload::toggledSoundEvent,
            SoundEvent.PACKET_CODEC, PressedAbilityKeyC2SPayload::activatedSoundEvent,
            PressedAbilityKeyC2SPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
