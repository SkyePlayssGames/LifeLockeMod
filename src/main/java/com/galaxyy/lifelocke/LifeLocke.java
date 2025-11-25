package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.command.DebugCommand;
import com.galaxyy.lifelocke.command.PsychicCommand;
import com.galaxyy.lifelocke.command.RollTypeCommand;
import com.galaxyy.lifelocke.command.TypeCommand;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.item.ModItems;
import com.galaxyy.lifelocke.itemgroup.ModItemGroups;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SPayload;
import com.galaxyy.lifelocke.networking.PressedAbilityKeyC2SHandler;
import com.galaxyy.lifelocke.power.*;
import com.galaxyy.lifelocke.triggers.GroundTrigger;
import com.galaxyy.lifelocke.util.PlayerCopyHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class LifeLocke implements ModInitializer {
	public static final String MOD_ID = "lifelocke";

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();

		PressedAbilityKeyC2SHandler.registerEffectMap();
		GroundTrigger.registerGroundMaps();

		ServerPlayerEvents.COPY_FROM.register(new PlayerCopyHandler());

		AttackEntityCallback.EVENT.register(new ElectricPower());
		AttackEntityCallback.EVENT.register(new IcePower());
		AttackEntityCallback.EVENT.register(new PoisonPower());
		AttackEntityCallback.EVENT.register(new DarkPower());
		AttackEntityCallback.EVENT.register(new PsychicPower());
		AttackEntityCallback.EVENT.register(new GrassPower());

		CommandRegistrationCallback.EVENT.register(new TypeCommand());
		CommandRegistrationCallback.EVENT.register(new PsychicCommand());
		CommandRegistrationCallback.EVENT.register(new RollTypeCommand());
		CommandRegistrationCallback.EVENT.register(new DebugCommand());

		PayloadTypeRegistry.playC2S().register(PressedAbilityKeyC2SPayload.ID, PressedAbilityKeyC2SPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(PressedAbilityKeyC2SPayload.ID, new PressedAbilityKeyC2SHandler());
	}
}