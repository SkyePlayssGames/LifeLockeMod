package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.command.DebugCommand;
import com.galaxyy.lifelocke.command.PsychicCommand;
import com.galaxyy.lifelocke.command.RollTypeCommand;
import com.galaxyy.lifelocke.command.TypeCommand;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.item.ModItems;
import com.galaxyy.lifelocke.itemgroup.ModItemGroups;
import com.galaxyy.lifelocke.power.*;
import com.galaxyy.lifelocke.util.TriggerEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
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
		AttackEntityCallback.EVENT.register(new DarkPower());
	//	PlayerBlockBreakEvents.AFTER.register(new RockPower());
		UseBlockCallback.EVENT.register(new TriggerEvent());

		CommandRegistrationCallback.EVENT.register(new TypeCommand());
		CommandRegistrationCallback.EVENT.register(new PsychicCommand());
		CommandRegistrationCallback.EVENT.register(new RollTypeCommand());
		CommandRegistrationCallback.EVENT.register(new DebugCommand());
	}
}