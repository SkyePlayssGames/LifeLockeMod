package com.galaxyy.lifelocke;

import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.command.*;
import com.galaxyy.lifelocke.damage.ChangeDamageEvent;
import com.galaxyy.lifelocke.damage.ModDamageTypes;
import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.entity.ModEntities;
import com.galaxyy.lifelocke.entity.custom.FireMobEntity;
import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import com.galaxyy.lifelocke.event.*;
import com.galaxyy.lifelocke.events.ModdedPlayerJoinServerCallback;
import com.galaxyy.lifelocke.events.ModdedPlayerJoinClientCallback;
import com.galaxyy.lifelocke.gamerule.ModGameRules;
import com.galaxyy.lifelocke.item.ModItems;
import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import com.galaxyy.lifelocke.itemgroup.ModItemGroups;
import com.galaxyy.lifelocke.modmenu.SettingsFileHandler;
import com.galaxyy.lifelocke.modmenu.settings.PowerSoundSetting;
import com.galaxyy.lifelocke.networking.*;
import com.galaxyy.lifelocke.power.*;
import com.galaxyy.lifelocke.sound.ModSounds;
import com.galaxyy.lifelocke.triggers.activated.GroundTrigger;
import com.galaxyy.lifelocke.playerdata.PlayerCopyHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class LifeLocke implements ModInitializer {
	public static final String MOD_ID = "lifelocke";
	public static final int CLIENT_VERSION = 14;
	public static final int SERVER_VERSION = 14;

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
		ModGameRules.registerGameRules();
		ModDataComponents.registerModDataComponents();
		ModSounds.registerSounds();
		ModEntities.registerModEntities();
		ModDamageTypes.registerSlainDamageTypes();
		ModDamageTypes.registerProjectileDamageTypes();
		ModDamageTypes.registerFallDamageTypes();

		PressedAbilityKeyC2SHandler.registerEffectMap();
		GroundTrigger.registerGroundMaps();
		PowerSoundSetting.registerSoundEventMaps();
		SettingsFileHandler.registerSettingsFile();
		JoinIconFixEvent.registerJoinFixEventTypes();

		ServerPlayerEvents.AFTER_RESPAWN.register(new PlayerCopyHandler());
		ServerPlayerEvents.JOIN.register(new JoinIconFixEvent());

		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new ElectricImmunityPower());
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new ChangeDamageEvent());
		ServerLivingEntityEvents.ALLOW_DEATH.register(new NormalPower());

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
		CommandRegistrationCallback.EVENT.register(new HealCommand());

		ServerTickEvents.END_WORLD_TICK.register(new RemoveIllegalItemsEvent());

		PayloadTypeRegistry.playC2S().register(PressedAbilityKeyC2SPayload.ID, PressedAbilityKeyC2SPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(ServerModCheckC2SPayload.ID, ServerModCheckC2SPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(RenderTypeIconS2CPayload.ID, RenderTypeIconS2CPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(ServerModCheckS2CPayload.ID, ServerModCheckS2CPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(GrassMobAnimationS2CPayload.ID, GrassMobAnimationS2CPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(CopyFromDeadPlayerS2CPayload.ID, CopyFromDeadPlayerS2CPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(PressedAbilityKeyC2SPayload.ID, new PressedAbilityKeyC2SHandler());
		ServerPlayNetworking.registerGlobalReceiver(ServerModCheckC2SPayload.ID, new ServerModCheckC2SHandler());

		ModdedPlayerJoinServerCallback.EVENT.register(new CheckServerVersionEvent());
		ModdedPlayerJoinClientCallback.EVENT.register(new CheckClientVersionEvent());

		ItemTooltipCallback.EVENT.register(new AddTooltipsEvent());

		FabricDefaultAttributeRegistry.register(ModEntities.FIRE_MOB, FireMobEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.GRASS_MOB, GrassMobEntity.createMobAttributes());
	}
}