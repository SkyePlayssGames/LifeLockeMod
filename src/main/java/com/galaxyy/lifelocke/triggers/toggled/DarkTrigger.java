package com.galaxyy.lifelocke.triggers.toggled;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.ToggledAbility;
import net.minecraft.server.level.ServerPlayer;
import com.galaxyy.lifelocke.playerdata.UpdateData;

public class DarkTrigger implements ToggledAbility {
    public boolean toggle(ServerPlayer playerEntity) {
        updateData(playerEntity, UpdateData::toggleDarkPower, ModEffects.DARK);
        return true;
    }
}
