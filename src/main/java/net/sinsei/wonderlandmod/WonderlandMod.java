package net.sinsei.wonderlandmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.fluid.ModFluidTypes;
import net.sinsei.wonderlandmod.fluid.ModFluids;
import net.sinsei.wonderlandmod.item.ModCreativeModTabs;
import net.sinsei.wonderlandmod.item.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WonderlandMod.MOD_ID)
public class WonderlandMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "wonderland_mod_id";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public WonderlandMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ForgeMod.enableMilkFluid();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
//            event.accept(ModItems.BLOCK_CHANGE_ITEM);
//            event.accept(ModItems.CAKE_SEEDS_ITEM);
//            event.accept(ModItems.CHOCOLATE_ITEM);
//            event.accept(ModItems.LOLLY_ITEM);
//            event.accept(ModItems.BURN_ITEM);
//            event.accept(ModItems.SOAP_WATER_BUCKET);
//            event.accept(ModItems.HONEY_BUCKET);
//            event.accept(ModItems.LIQUID_SUGAR_BUCKET);
//
//            event.accept(ModBlocks.BLOCK_CHANGE_BLOCK);
//            event.accept(ModBlocks.CAKE_CROP_BLOCK);
//            event.accept(ModBlocks.CHOCOLATE_BLOCK);
//            event.accept(ModBlocks.BISCUIT_BLOCK);
//            event.accept(ModBlocks.SWEET_DIRT_BLOCK);
//            event.accept(ModBlocks.SWEET_GRASS_BLOCK);
//            event.accept(ModBlocks.SWEET_FARM_BLOCK);
            //event.accept(ModBlocks.SOAP_WATER_BLOCK);

        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());
        }
    }
}
