package com.galaxyy.lifelocke.triggers.toggled;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.triggers.ToggledAbility;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class ElectricTrigger implements ToggledAbility {
    public boolean toggle(ServerPlayerEntity playerEntity) {
        updateData(playerEntity, UpdateData::toggleElectricPower, ModEffects.ELECTRIC);
        return true;
    }
}
