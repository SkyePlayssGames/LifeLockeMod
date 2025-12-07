package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ServerModCheckC2SPayload() implements CustomPayload {
    public static final Identifier SERVER_MOD_CHECK = Identifier.of(LifeLocke.MOD_ID, "server_mod_check_packet");
    public static final Id<ServerModCheckC2SPayload> ID = new Id<>(SERVER_MOD_CHECK);
    public static final PacketCodec<RegistryByteBuf, ServerModCheckC2SPayload> CODEC = PacketCodec.unit(
            new ServerModCheckC2SPayload()
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
