package net.sinsei.wonderlandmod.Blocks.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.sinsei.wonderlandmod.item.ModItems;
import net.sinsei.wonderlandmod.util.DevUtil;

import java.util.function.Supplier;

public class HoneyFluidBlock extends LiquidBlock
{
    public HoneyFluidBlock(Supplier<? extends ForgeFlowingFluid> supplier, Properties properties)
    {
        super(supplier, properties);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos position, Entity entity)
    {
        if (entity instanceof Player player)
        {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0, false, true));
            player.heal(1);
            // Todo Custom Food for this
            player.eat(world, new ItemStack(ModItems.CHOCOLATE_ITEM.get()));
        }

        super.entityInside(state, world, position, entity);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        DevUtil.outputDevMessage("honeyTickBlock0");
    }
}
