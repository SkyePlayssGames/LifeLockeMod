package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ClientDisconnectC2SPacket() implements CustomPacketPayload {
    public static final Identifier CLIENT_DISCONNECT = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "client_disconnect_packet");
    public static final Type<ClientDisconnectC2SPacket> ID = new Type<>(CLIENT_DISCONNECT);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientDisconnectC2SPacket> CODEC = StreamCodec.unit(
            new ClientDisconnectC2SPacket()
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
