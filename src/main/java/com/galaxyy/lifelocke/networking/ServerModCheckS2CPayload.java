package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ServerModCheckS2CPayload(int serverVersion) implements CustomPayload {
    public static final Identifier SERVER_MOD_CHECK_RETURN = Identifier.of(LifeLocke.MOD_ID, "server_mod_check_return_packet");
    public static final Id<ServerModCheckS2CPayload> ID = new Id<>(SERVER_MOD_CHECK_RETURN);
    public static final PacketCodec<RegistryByteBuf, ServerModCheckS2CPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, ServerModCheckS2CPayload::serverVersion,
            ServerModCheckS2CPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
