package com.galaxyy.lifelocke.triggers.toggled;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.ToggledAbility;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.minecraft.server.network.ServerPlayerEntity;

public class GhostTrigger implements ToggledAbility {
    public boolean toggle(ServerPlayerEntity playerEntity) {
        updateData(playerEntity, UpdateData::toggleGhostPower, ModEffects.GHOST);
        return true;
    }
}
