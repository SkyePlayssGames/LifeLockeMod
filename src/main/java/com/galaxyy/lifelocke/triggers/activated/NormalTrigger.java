package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.gamerule.ModGameRules;
import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3i;

public class NormalTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayerEntity playerEntity, Vec3i pos) {
        if (!playerEntity.getEntityWorld().getGameRules().getValue(ModGameRules.NORMAL_HAS_ABILITY)) {
            return false;
        }

        ItemStack heldItem = playerEntity.getMainHandStack();
        ItemStack storedItem = UpdateData.swapNormalItemStack(playerEntity, heldItem);
        playerEntity.setStackInHand(playerEntity.getActiveHand(), storedItem);

        return true;
    }
}
