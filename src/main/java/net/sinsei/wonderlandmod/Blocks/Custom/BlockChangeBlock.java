package net.sinsei.wonderlandmod.Blocks.Custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockChangeBlock extends Block
{
    public static final int RADIUS = 6;

    public BlockChangeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity)
    {
        if(!pLevel.isClientSide())
        {
            if(pEntity instanceof Player player)
            {
                for (int x = RADIUS; x >= -RADIUS; x -= 1) {
                    for (int y = RADIUS; y >= -RADIUS; y -= 1) {
                        for (int z = RADIUS; z >= -RADIUS; z -= 1) {
                            if ((Math.abs(x * x) + Math.abs(y * y) + Math.abs(z * z)) <= (RADIUS * RADIUS) + RADIUS) {
                                BlockPos pos = pPos.offset(x, y, z);
                                BlockState state = pLevel.getBlockState(pos);
                                if (isPossibleBlock(state)) {
                                    outputDirtVariables(pos, player, state.getBlock());
                                    pLevel.setBlock(pos, ModBlocks.CHOCOLATE_BLOCK.get().defaultBlockState(), 3);
                                }
                            }
                        }
                    }
                }
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void outputDirtVariables(BlockPos blockPos, Player player, Block block)
    {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private Boolean isPossibleBlock(BlockState state)
    {
        return state.is(ModTags.Blocks.BLOCK_CHANGE_ITEM_POSS);
    }
}

