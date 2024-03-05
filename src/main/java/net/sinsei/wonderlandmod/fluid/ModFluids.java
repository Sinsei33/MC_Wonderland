package net.sinsei.wonderlandmod.fluid;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.Custom.HoneyFluid;
import net.sinsei.wonderlandmod.item.ModItems;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModFluids
{
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, WonderlandMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_SOAP_WATER = FLUIDS.register("soap_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SOAP_WATER = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HONEY_FLUID_SOURCE = FLUIDS.register("honey_fluid_source",
            () -> new ForgeFlowingFluid.Source(HoneyFluid.HONEY_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HONEY_FLUID_FLOW = FLUIDS.register("honey_fluid_flow",
            () -> new ForgeFlowingFluid.Flowing(HoneyFluid.HONEY_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.SOAP_WATER_BLOCK).bucket(ModItems.SOAP_WATER_BUCKET);

    public static void register(IEventBus eventBus)
    {
        FLUIDS.register(eventBus);
    }
}

//package cy.jdkdigital.productivebees.init;
//
//import cy.jdkdigital.productivebees.ProductiveBees;
//import cy.jdkdigital.productivebees.common.fluid.HoneyFluid;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.material.Fluid;
//import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
//import net.minecraftforge.fluids.FluidType;
//import net.minecraftforge.fluids.ForgeFlowingFluid;
//import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
//@Mod.EventBusSubscriber(modid = ProductiveBees.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public final class ModFluids
//{
//    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ProductiveBees.MODID);
//    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ProductiveBees.MODID);
//
//    public static final RegistryObject<ForgeFlowingFluid> HONEY = createFluid("honey", HoneyFluid.Source::new);
//    public static final RegistryObject<ForgeFlowingFluid> HONEY_FLOWING = createFluid("flowing_honey", HoneyFluid.Flowing::new);
//
//    private static <B extends Fluid> RegistryObject<B> createFluid(String name, Supplier<? extends B> supplier) {
//        return FLUIDS.register(name, supplier);
//    }
//
//    public static RegistryObject<FluidType> HONEY_FLUID_TYPE = FLUID_TYPES.register("honey", () -> new FluidType(FluidType.Properties.create().canExtinguish(true).supportsBoating(true).motionScale(0.007D))
//    {
//        @Override
//        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
//            consumer.accept(new IClientFluidTypeExtensions() {
//                @Override
//                public ResourceLocation getStillTexture() {
//                    return HoneyFluid.STILL;
//                }
//
//                @Override
//                public ResourceLocation getFlowingTexture() {
//                    return HoneyFluid.FLOWING;
//                }
//
//                @Override
//                public ResourceLocation getOverlayTexture() {
//                    return HoneyFluid.OVERLAY;
//                }
//
//                @Override
//                public int getTintColor() {
//                    return 0xffffc916;
//                }
//            });
//        }
//    });
//}
