package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Vec3i;

public record RenderTypeIconS2CPayload(int icon) implements CustomPayload {
    public enum ICONS {
        NONE,
        ELECTRIC,
        ICE,
        POISON,
        DARK,
        PSYCHIC
    }


    public static final Identifier RENDER_TYPE_ICON = Identifier.of(LifeLocke.MOD_ID, "render_type_icon_packet");
    public static final Id<RenderTypeIconS2CPayload> ID = new Id<>(RENDER_TYPE_ICON);
    public static final PacketCodec<RegistryByteBuf, RenderTypeIconS2CPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, RenderTypeIconS2CPayload::icon, RenderTypeIconS2CPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
