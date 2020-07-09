package com.mactso.oldgods.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mactso.oldgods.Main;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

// @Mod.EventBusSubscriber(modid = Main.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class MyConfig
{
	private static final Logger LOGGER = LogManager.getLogger();
	public static final Server SERVER;
	public static final ForgeConfigSpec SERVER_SPEC;
	static
	{
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		SERVER_SPEC = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	public static int ticksOfReward;
	public static int ticksOfPunish;
	public static int tickEffectTime;
	public static String offeringItem;
	public static String rewardEffect;
	public static String punishEffect;

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent)
	{
		if (configEvent.getConfig().getSpec() == MyConfig.SERVER_SPEC)
		{
			bakeConfig();
		}
	}

	public static void bakeConfig()
	{
		ticksOfReward = SERVER.ticksOfReward.get();
		ticksOfPunish = SERVER.ticksOfPunish.get();
		tickEffectTime = SERVER.tickEffectTime.get();
		offeringItem = SERVER.offeringItem.get();
		rewardEffect = SERVER.rewardEffect.get();
		punishEffect = SERVER.punishEffect.get();
	}


	private static void validateMods(HashSet<String> set, String configName)
	{
		ModList list = ModList.get();
		set.removeIf(name -> {
			if (name.equals("*"))
				return false;
			if (list.isLoaded(name))
				return false;
			LOGGER.warn("Unknown entry in " + configName + ": " + name);
			return true;
		});
	}


	public static class Server
	{

		public final IntValue ticksOfReward;
		public final IntValue ticksOfPunish;
		public final IntValue tickEffectTime;
		public final ConfigValue<String> offeringItem;
		public final ConfigValue<String> rewardEffect;
		public final ConfigValue<String> punishEffect;
	

		public Server(ForgeConfigSpec.Builder builder)
		{
			String baseTrans = Main.MODID + ".config.";
			String sectionTrans;

			sectionTrans = baseTrans + "general.";
			ticksOfReward = builder
					.comment("Reward How Many Ticks")
					.translation(sectionTrans + "tickReward")
					.defineInRange("tickReward", 3600, 1, 12000);

			ticksOfPunish = builder
					.comment("Punish How Many Ticks")
					.translation(sectionTrans + "tickPunish")
					.defineInRange("tickPunish", 3600, 1, 12000);
			
			tickEffectTime = builder
					.comment("When To Punish or Reward in Ticks")
					.translation(sectionTrans + "tickEffectTime")
					.defineInRange("tickEffectTime", 9000, 1, 23999);
			
//			fixed = builder
//					.comment("Use fixed items")
//					.translation(sectionTrans + "use_fixed")
//					.define("UseFixedItems", false);

			offeringItem = builder
					.comment("Offering Item")
					.translation(sectionTrans + "offeringItem")
					.define("offeringItem", "minecraft:gold_block");

			rewardEffect = builder
					.comment("Reward Potion Effect")
					.translation(sectionTrans + "rewardEffect")
					.define("rewardEffect", "minecraft:absorption;2");

			punishEffect = builder
					.comment("Punish Potion Effect")
					.translation(sectionTrans + "punishEffect")
					.define("punishEffect", "minecraft:slowness;1");

			builder.pop();
		}
	}
}
