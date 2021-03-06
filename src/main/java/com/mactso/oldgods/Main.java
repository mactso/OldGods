package com.mactso.oldgods;

import com.mactso.oldgods.config.MyConfig;
import com.mactso.oldgods.world.ModVillage;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "oldgods";

    public Main()
    {
    	FMLJavaModLoadingContext.get().getModEventBus().register(this);
//		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MyConfig.SERVER_SPEC);
    }

	@SubscribeEvent
	public void setupCommon(final FMLCommonSetupEvent event)
	{
		ModVillage.updatePools();
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void setupClient(final FMLClientSetupEvent event)
    {
	//	ModBlocks.setRenderLayer();
	}

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents
    {
	    @SubscribeEvent
	    public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
	    {
	//        ModItems.register(event.getRegistry());
	    }

	    @SubscribeEvent
	    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
	    {
	 //       ModBlocks.register(event.getRegistry());
	    }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onColorsRegistry(final ColorHandlerEvent.Item event)
        {
     //   	ModItems.register(event.getItemColors());
        }

        @SubscribeEvent
        public static void onSoundRegistry(final RegistryEvent.Register<SoundEvent> event)
        {
     //   	ModSounds.register(event.getRegistry());
        }

	    @SubscribeEvent
	    public static void onTileEntitiesRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
	    {
	  //      ModTileEntities.register(event.getRegistry());
	    }
    }
}
