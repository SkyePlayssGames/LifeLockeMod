package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record CopyFromDeadPlayerS2CPayload(CompoundTag old_player) implements CustomPacketPayload {
    public static final Identifier COPY_FROM_DEAD_PLAYER = Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "copy_from_dead_player");
    public static final Type<CopyFromDeadPlayerS2CPayload> ID = new Type<>(COPY_FROM_DEAD_PLAYER);
    public static final StreamCodec<RegistryFriendlyByteBuf, CopyFromDeadPlayerS2CPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG, CopyFromDeadPlayerS2CPayload::old_player,
            CopyFromDeadPlayerS2CPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
