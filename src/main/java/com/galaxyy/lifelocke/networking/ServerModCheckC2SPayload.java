package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import java.util.UUID;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ServerModCheckC2SPayload(int clientVersion) implements CustomPacketPayload {
    public static final Identifier SERVER_MOD_CHECK = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "server_mod_check_packet");
    public static final Type<ServerModCheckC2SPayload> ID = new Type<>(SERVER_MOD_CHECK);
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerModCheckC2SPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ServerModCheckC2SPayload::clientVersion,
            ServerModCheckC2SPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
