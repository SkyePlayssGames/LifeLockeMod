package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ServerModCheckS2CPayload(int serverVersion) implements CustomPacketPayload {
    public static final Identifier SERVER_MOD_CHECK_RETURN = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "server_mod_check_return_packet");
    public static final Type<ServerModCheckS2CPayload> ID = new Type<>(SERVER_MOD_CHECK_RETURN);
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerModCheckS2CPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ServerModCheckS2CPayload::serverVersion,
            ServerModCheckS2CPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
