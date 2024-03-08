package net.sinsei.wonderlandmod.fluid;


import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.Custom.HoneyFluid;
import net.sinsei.wonderlandmod.fluid.Custom.LiquidSugar;
import net.sinsei.wonderlandmod.item.ModItems;

import java.util.function.Supplier;

public class ModFluids
{
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, WonderlandMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_SOAP_WATER = FLUIDS.register("soap_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SOAP_WATER = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.SOAP_WATER_BLOCK).bucket(ModItems.SOAP_WATER_BUCKET);



    public static final RegistryObject<ForgeFlowingFluid> HONEY_FLUID_SOURCE = register("honey_fluid_source", HoneyFluid.Source::new);
    public static final RegistryObject<ForgeFlowingFluid> HONEY_FLUID_FLOW = register("honey_fluid_flow", HoneyFluid.Flowing::new);

    public static final RegistryObject<ForgeFlowingFluid> LIQUID_SUGAR_FLUID_SOURCE = register("liquid_sugar_fluid_source", LiquidSugar.Source::new);
    public static final RegistryObject<ForgeFlowingFluid> LIQUID_SUGAR_FLUID_FLOW = register("liquid_sugar_fluid_flow", LiquidSugar.Flowing::new);




    public static void register(IEventBus eventBus)
    {
        FLUIDS.register(eventBus);
    }
    private static <B extends Fluid> RegistryObject<B> register(String name, Supplier<? extends B> supplier) {
        return FLUIDS.register(name, supplier);
    }
}