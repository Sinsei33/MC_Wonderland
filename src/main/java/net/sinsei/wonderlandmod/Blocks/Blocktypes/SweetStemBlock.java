package net.sinsei.wonderlandmod.Blocks.Blocktypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;

import java.util.function.Supplier;

public class SweetStemBlock extends StemBlock
{
    protected final StemGrownBlock fruit;
    protected final Supplier<Item> seedSupplier;

    public SweetStemBlock(StemGrownBlock pFruit, Supplier<Item> pSeedSupplier, BlockBehaviour.Properties pProperties)
    {
        super(pFruit, pSeedSupplier, pProperties);
        this.fruit = pFruit;
        this.seedSupplier = pSeedSupplier;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos)
    {
        BlockPos blockpos = pPos.below();
        if (pState.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return pLevel.getBlockState(blockpos).is(ModBlocks.SWEET_FARM_BLOCK.get());

        return pState.is(ModBlocks.SWEET_FARM_BLOCK.get());
    }

    // TODO
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            float f = getGrowthSpeed(this, pLevel, pPos);
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int)(25.0F / f) + 1) == 0)) {
                int i = pState.getValue(AGE);
                if (i < 7) {
                    pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(i + 1)), 2);
                } else {
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
                    BlockPos blockpos = pPos.relative(direction);
                    BlockState blockstate = pLevel.getBlockState(blockpos.below());
                    if (pLevel.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(pLevel, blockpos.below(), Direction.UP, this.fruit) || blockstate.is(Blocks.FARMLAND) || blockstate.is(BlockTags.DIRT))) {
                        pLevel.setBlockAndUpdate(blockpos, this.fruit.defaultBlockState());
                        pLevel.setBlockAndUpdate(pPos, this.fruit.getAttachedStem().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                    }
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
            }

        }
    }

    protected static float getGrowthSpeede(Block pBlock, BlockGetter pLevel, BlockPos pPos) {
        float f = 1.0F;
        BlockPos blockpos = pPos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = pLevel.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(pLevel, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, (net.minecraftforge.common.IPlantable) pBlock)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(pLevel, pPos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pPos.north();
        BlockPos blockpos2 = pPos.south();
        BlockPos blockpos3 = pPos.west();
        BlockPos blockpos4 = pPos.east();
        boolean flag = pLevel.getBlockState(blockpos3).is(pBlock) || pLevel.getBlockState(blockpos4).is(pBlock);
        boolean flag1 = pLevel.getBlockState(blockpos1).is(pBlock) || pLevel.getBlockState(blockpos2).is(pBlock);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = pLevel.getBlockState(blockpos3.north()).is(pBlock) || pLevel.getBlockState(blockpos4.north()).is(pBlock) || pLevel.getBlockState(blockpos4.south()).is(pBlock) || pLevel.getBlockState(blockpos3.south()).is(pBlock);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(ModBlocks.SWEET_FARM_BLOCK.get());
    }

}
