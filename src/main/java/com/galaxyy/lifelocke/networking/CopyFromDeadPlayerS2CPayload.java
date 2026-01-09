package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record CopyFromDeadPlayerS2CPayload(NbtCompound old_player) implements CustomPayload {
    public static final Identifier COPY_FROM_DEAD_PLAYER = Identifier.of(LifeLocke.MOD_ID, "copy_from_dead_player");
    public static final Id<CopyFromDeadPlayerS2CPayload> ID = new Id<>(COPY_FROM_DEAD_PLAYER);
    public static final PacketCodec<RegistryByteBuf, CopyFromDeadPlayerS2CPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.NBT_COMPOUND, CopyFromDeadPlayerS2CPayload::old_player,
            CopyFromDeadPlayerS2CPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
