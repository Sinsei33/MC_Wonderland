package net.sinsei.wonderlandmod.fluid.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
import net.sinsei.wonderlandmod.util.DevUtil;
import net.sinsei.wonderlandmod.util.ModTags;

import javax.annotation.Nullable;

public abstract class LiquidSugar extends ForgeFlowingFluid
{
    public static final int RADIUS = 1;

    public static final ResourceLocation STILL = new ResourceLocation(WonderlandMod.MOD_ID, "block/liquid_sugar/still");
    public static final ResourceLocation FLOWING = new ResourceLocation(WonderlandMod.MOD_ID, "block/liquid_sugar/flow");
    public static final ResourceLocation OVERLAY = new ResourceLocation(WonderlandMod.MOD_ID, "block/liquid_sugar/overlay");


    public static BaseFluidType GETFLUIDTYPE()
    {
        return new BaseFluidType(STILL, FLOWING, OVERLAY, 0xA1ded6d3,
                new Vec3(222f / 255f, 214f / 255f, 211f / 255f),
                FluidType.Properties.create().lightLevel(15).density(2).viscosity(1).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    }

    public static Properties GETPROPERTIES ()
    {
        return new Properties(
                ModFluidTypes.LIQUID_SUGAR_FLUID_TYPE,
                ModFluids.LIQUID_SUGAR_FLUID_SOURCE,
                ModFluids.LIQUID_SUGAR_FLUID_FLOW
                ).bucket(ModItems.LIQUID_SUGAR_BUCKET).block(ModBlocks.LIQUID_SUGAR_BLOCK);
    }

    public LiquidSugar() {
        super(GETPROPERTIES());
    }



    @Override
    public Fluid getFlowing() {
        return ModFluids.LIQUID_SUGAR_FLUID_FLOW.get();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.LIQUID_SUGAR_FLUID_SOURCE.get();
    }

    @Override
    public void animateTick(Level worldIn, BlockPos pos, FluidState state, RandomSource random) {
        BlockPos blockpos = pos.above();
        if (random.nextInt(100) == 0 && !worldIn.getBlockState(blockpos).getFluidState().equals(state)) {
            worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.HONEY_BLOCK_SLIDE, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
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
        return fluidIn.is(ModTags.Fluids.SWEETS_FLUID_TAG);
    }

    @Override
    public int getDropOff(LevelReader worldIn) {
        return worldIn.dimensionType().ultraWarm() ? 1 : 2;
    }

    @Override
    public int getTickDelay(LevelReader worldIn) {
        return worldIn.dimensionType().ultraWarm() ? 1 : 3;
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

    public static class Flowing extends LiquidSugar
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

        @Override
        public void randomTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom)
        {
            if (!pLevel.isAreaLoaded(pPos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9)
            {
                for(int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(5) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(5) - 1);
                    BlockState state = pLevel.getBlockState(blockpos);
                    changeBlock(pLevel, blockpos, state);
                }
            }
        }

        private void changeBlock(Level pLevel, BlockPos pos, BlockState state)
        {
            if(state.is(Blocks.DIRT))
                pLevel.setBlock(pos, ModBlocks.SWEET_DIRT_BLOCK.get().defaultBlockState(), 3);
            else if(state.is(Blocks.GRASS_BLOCK))
                pLevel.setBlock(pos, ModBlocks.SWEET_GRASS_BLOCK.get().defaultBlockState(), 3);
            else if(state.is(Blocks.FARMLAND))
                pLevel.setBlock(pos, ModBlocks.SWEET_FARM_BLOCK.get().defaultBlockState(), 3);
            // TODO PATH
        }
    }

    public static class Source extends LiquidSugar
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

        @Override
        public void randomTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom)
        {
            if (!pLevel.isAreaLoaded(pPos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9)
            {
                for(int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(5) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(5) - 1);
                    BlockState state = pLevel.getBlockState(blockpos);
                    changeBlock(pLevel, blockpos, state);
                }
            }
        }

        private void changeBlock(Level pLevel, BlockPos pos, BlockState state)
        {
            if(state.is(Blocks.DIRT))
                pLevel.setBlock(pos, ModBlocks.SWEET_DIRT_BLOCK.get().defaultBlockState(), 3);
            else if(state.is(Blocks.GRASS_BLOCK))
                pLevel.setBlock(pos, ModBlocks.SWEET_GRASS_BLOCK.get().defaultBlockState(), 3);
            else if(state.is(Blocks.FARMLAND))
                pLevel.setBlock(pos, ModBlocks.SWEET_FARM_BLOCK.get().defaultBlockState(), 3);
            // TODO PATH
        }
    }
}