package com.galaxyy.lifelocke.triggers.toggled;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.ToggledAbility;
import net.minecraft.server.level.ServerPlayer;
import com.galaxyy.lifelocke.playerdata.UpdateData;

public class PoisonTrigger implements ToggledAbility {
    public boolean toggle(ServerPlayer playerEntity) {
        updateData(playerEntity, UpdateData::togglePoisonPower, ModEffects.POISON);
        return true;
    }
}
