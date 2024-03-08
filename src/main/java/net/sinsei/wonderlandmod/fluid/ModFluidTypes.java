package net.sinsei.wonderlandmod.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.Custom.HoneyFluid;
import net.sinsei.wonderlandmod.fluid.Custom.LiquidSugar;

public class ModFluidTypes
{
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(WonderlandMod.MOD_ID, "misc/in_soap_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, WonderlandMod.MOD_ID);

    public static final RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE = register("soap_water_fluid",
            new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL, 0xA1E038D0,
                    new Vec3(224f / 225f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(15).density(15).viscosity(5).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK)));



    public static final RegistryObject<FluidType> HONEY_FLUID_TYPE = register("honey_fluid",
            HoneyFluid.GETFLUIDTYPE());

    public static final RegistryObject<FluidType> LIQUID_SUGAR_FLUID_TYPE = register("liquid_sugar_fluid",
            LiquidSugar.GETFLUIDTYPE());





    private static RegistryObject<FluidType> register(String name,
                                                      BaseFluidType type)
    {
        return FLUID_TYPES.register(name, () -> type);
    }

    public static void register(IEventBus eventBus)
    {
        FLUID_TYPES.register(eventBus);
    }
}
