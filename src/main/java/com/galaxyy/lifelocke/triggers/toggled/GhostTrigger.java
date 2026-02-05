package com.galaxyy.lifelocke.triggers.toggled;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.triggers.ToggledAbility;
import net.minecraft.server.level.ServerPlayer;
import com.galaxyy.lifelocke.playerdata.UpdateData;

public class GhostTrigger implements ToggledAbility {
    public boolean toggle(ServerPlayer playerEntity) {
        updateData(playerEntity, UpdateData::toggleGhostPower, Types.GHOST_TYPE.type);
        return true;
    }
}
