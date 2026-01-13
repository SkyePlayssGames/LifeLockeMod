package com.galaxyy.lifelocke.triggers.activated;

import com.galaxyy.lifelocke.gamerule.ModGameRules;
import com.galaxyy.lifelocke.triggers.ActivatedAbility;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import com.galaxyy.lifelocke.playerdata.UpdateData;

public class NormalTrigger implements ActivatedAbility {
    @Override
    public boolean activate(ServerPlayer playerEntity, Vec3i pos) {
        if (!playerEntity.level().getGameRules().get(ModGameRules.NORMAL_HAS_ABILITY)) {
            return false;
        }

        ItemStack heldItem = playerEntity.getMainHandItem();
        ItemStack storedItem = UpdateData.swapNormalItemStack(playerEntity, heldItem);
        playerEntity.setItemInHand(playerEntity.getUsedItemHand(), storedItem);

        return true;
    }
}
