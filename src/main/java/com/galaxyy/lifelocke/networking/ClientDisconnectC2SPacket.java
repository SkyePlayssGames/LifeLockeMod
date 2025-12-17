package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ClientDisconnectC2SPacket() implements CustomPayload {
    public static final Identifier CLIENT_DISCONNECT = Identifier.of(LifeLocke.MOD_ID, "client_disconnect_packet");
    public static final Id<ClientDisconnectC2SPacket> ID = new Id<>(CLIENT_DISCONNECT);
    public static final PacketCodec<RegistryByteBuf, ClientDisconnectC2SPacket> CODEC = PacketCodec.unit(
            new ClientDisconnectC2SPacket()
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
