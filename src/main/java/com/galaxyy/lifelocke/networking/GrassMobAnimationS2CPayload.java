package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;

public record GrassMobAnimationS2CPayload(UUID uuid, String animation) implements CustomPacketPayload {
    public enum ANIMATION implements StringRepresentable {
        HIDE,
        UNHIDE,
        MAGIC_ATTACK;

        @Override
        public String getSerializedName() {
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

    public static final Identifier GRASS_MOB_HIDES = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "grass_mob_hides");
    public static final Type<GrassMobAnimationS2CPayload> ID = new Type<>(GRASS_MOB_HIDES);
    public static final StreamCodec<RegistryFriendlyByteBuf, GrassMobAnimationS2CPayload> CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, GrassMobAnimationS2CPayload::uuid,
            ByteBufCodecs.STRING_UTF8, GrassMobAnimationS2CPayload::animation,
            GrassMobAnimationS2CPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
