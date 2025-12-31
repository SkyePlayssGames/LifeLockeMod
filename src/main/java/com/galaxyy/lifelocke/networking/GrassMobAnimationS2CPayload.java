package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record GrassMobAnimationS2CPayload(UUID uuid, String animation) implements CustomPayload {
    public enum ANIMATION implements StringIdentifiable {
        HIDE,
        UNHIDE,
        MAGIC_ATTACK;

        @Override
        public String asString() {
            return switch (this) {
                case HIDE -> "hide";
                case UNHIDE -> "unhide";
                case MAGIC_ATTACK -> "magic_attack";
            };
        }

        public static ANIMATION fromString(String string) {
            return switch (string) {
                case "hide" -> HIDE;
                case "unhide" -> UNHIDE;
                case "magic_attack" -> MAGIC_ATTACK;
                default -> null;
            };
        }
    }

    public static final Identifier GRASS_MOB_HIDES = Identifier.of(LifeLocke.MOD_ID, "grass_mob_hides");
    public static final Id<GrassMobAnimationS2CPayload> ID = new Id<>(GRASS_MOB_HIDES);
    public static final PacketCodec<RegistryByteBuf, GrassMobAnimationS2CPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC, GrassMobAnimationS2CPayload::uuid,
            PacketCodecs.STRING, GrassMobAnimationS2CPayload::animation,
            GrassMobAnimationS2CPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
