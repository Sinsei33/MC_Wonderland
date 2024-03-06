package net.sinsei.wonderlandmod.fluid.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.BaseFluidType;
import net.sinsei.wonderlandmod.fluid.ModFluidTypes;
import net.sinsei.wonderlandmod.fluid.ModFluids;
import net.sinsei.wonderlandmod.item.ModItems;
import net.sinsei.wonderlandmod.util.ModTags;

import javax.annotation.Nullable;

public abstract class LiquidStarlight extends ForgeFlowingFluid
{
    public static final ResourceLocation STILL = new ResourceLocation(WonderlandMod.MOD_ID, "block/honey/honey_still");
    public static final ResourceLocation FLOWING = new ResourceLocation(WonderlandMod.MOD_ID, "block/honey/honey_flow");
    public static final ResourceLocation OVERLAY = new ResourceLocation(WonderlandMod.MOD_ID, "block/honey/overlay");


    public static BaseFluidType GETFLUIDTYPE()
    {
        return new BaseFluidType(STILL, FLOWING, OVERLAY, 0xA1f5f0c9,
                new Vec3(245f / 225f, 240f / 255f, 201f / 255f),
                FluidType.Properties.create().lightLevel(15).density(2).viscosity(1).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    }

    public static Properties GETPROPERTIES ()
    {
        return new Properties(
                ModFluidTypes.LIQUID_STARLIGHT_FLUID_TYPE,
                ModFluids.LIQUID_STARLIGHT_FLUID_SOURCE,
                ModFluids.LIQUID_STARLIGHT_FLUID_FLOW
                ).bucket(ModItems.LIQUID_STARLIGHT_BUCKET).block(ModBlocks.LIQUID_STARLIGHT_BLOCK);
    }

    public LiquidStarlight() {
        super(GETPROPERTIES());
    }



    @Override
    public Fluid getFlowing() {
        return ModFluids.LIQUID_STARLIGHT_FLUID_FLOW.get();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.LIQUID_STARLIGHT_FLUID_SOURCE.get();
    }

    @Override
    public void animateTick(Level worldIn, BlockPos pos, FluidState state, RandomSource random) {
        BlockPos blockpos = pos.above();
        if (random.nextInt(100) == 0 && !worldIn.getBlockState(blockpos).getFluidState().equals(state)) {
            worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.HONEY_BLOCK_SLIDE, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }
    }

    @Nullable
    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_HONEY;
    }

    @Override
    public int getSlopeFindDistance(LevelReader worldIn) {
        return worldIn.dimensionType().ultraWarm() ? 6 : 3;
    }

    @Override
    public boolean isSame(Fluid fluidIn) {
        return fluidIn.is(ModTags.Fluids.SWEETS_TAG);
    }

    @Override
    public int getDropOff(LevelReader worldIn) {
        return worldIn.dimensionType().ultraWarm() ? 1 : 2;
    }

    @Override
    public int getTickDelay(LevelReader worldIn) {
        return worldIn.dimensionType().ultraWarm() ? 10 : 30;
    }

    @Override
    public int getSpreadDelay(Level world, BlockPos pos, FluidState state, FluidState FluidState) {
        int i = this.getTickDelay(world);
        if (!state.isEmpty() && !FluidState.isEmpty() && !state.getValue(FALLING) && !FluidState.getValue(FALLING) && FluidState.getHeight(world, pos) > state.getHeight(world, pos) && world.getRandom().nextInt(4) != 0) {
            i *= 4;
        }

        return i;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

//    public boolean shouldFreeze(LevelReader pLevel, Biome biome, BlockPos pos) {
//        if (!biome.warmEnoughToRain(pos)) {
//            if (pos.getY() >= pLevel.getMinBuildHeight() && pos.getY() < pLevel.getMaxBuildHeight() && pLevel.getBrightness(LightLayer.BLOCK, pos) < 10) {
//                BlockState blockstate = pLevel.getBlockState(pos);
//                FluidState fluidstate = pLevel.getFluidState(pos);
//                if (fluidstate.getType() == ModFluids.HONEY.get() && blockstate.getBlock() instanceof LiquidBlock) {
//                    boolean flag = this.isHoneyAt(pLevel, pos.west()) && this.isHoneyAt(pLevel, pos.east()) && this.isHoneyAt(pLevel, pos.north()) && this.isHoneyAt(pLevel, pos.south());
//                    if (!flag) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean isHoneyAt(LevelReader level, BlockPos pPos) {
//        return level.getFluidState(pPos).is(ModTags.HONEY);
//    }

    public static class Flowing extends LiquidStarlight
    {
        public Flowing() {
            super();
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends LiquidStarlight
    {
        public Source() {
            super();
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}