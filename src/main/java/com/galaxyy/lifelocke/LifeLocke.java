package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.item.ModItems;
import com.galaxyy.lifelocke.itemgroup.ModItemGroups;
import com.galaxyy.lifelocke.power.IcePower;
import com.galaxyy.lifelocke.power.PoisonPower;
import com.galaxyy.lifelocke.util.TriggerEvent;
import com.galaxyy.lifelocke.power.ElectricPower;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class LifeLocke implements ModInitializer {
	public static final String MOD_ID = "lifelocke";

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();

		AttackEntityCallback.EVENT.register(new ElectricPower());
		AttackEntityCallback.EVENT.register(new IcePower());
		AttackEntityCallback.EVENT.register(new PoisonPower());
		UseBlockCallback.EVENT.register(new TriggerEvent());
	}
}