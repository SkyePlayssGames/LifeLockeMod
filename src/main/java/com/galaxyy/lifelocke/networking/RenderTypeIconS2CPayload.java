package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record RenderTypeIconS2CPayload(Identifier id) implements CustomPacketPayload {


    public static final Identifier RENDER_TYPE_ICON = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "render_type_icon_packet");
    public static final Type<RenderTypeIconS2CPayload> ID = new Type<>(RENDER_TYPE_ICON);
    public static final StreamCodec<RegistryFriendlyByteBuf, RenderTypeIconS2CPayload> CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, RenderTypeIconS2CPayload::id, RenderTypeIconS2CPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
