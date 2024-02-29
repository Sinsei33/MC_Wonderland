package net.sinsei.wonderlandmod.item.Custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BlockChangeItem extends Item
{
    public static final int RADIUS = 6;

    public BlockChangeItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide())
        {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int y = (RADIUS - 1); y >= 0; y -= 1)
            {
                int x = (RADIUS - 1) - y;

                do {
                    int z = (RADIUS - 1) - y - x;

                    do {
                        if(x == 0 && z == 0)
                        {
                            BlockState y_state = pContext.getLevel().getBlockState(positionClicked.offset(0, y, 0));
                            if(isGrassBlock(y_state))
                            {
                                outputDirtVariables(positionClicked.offset(0, y, 0), player, y_state.getBlock());
                            }
                        }
                        else if(x != 0 && z != 0)
                        {
                            BlockState x1_z1_state = pContext.getLevel().getBlockState(positionClicked.offset(x,y,z));
                            BlockState x2_z1_state = pContext.getLevel().getBlockState(positionClicked.offset(-x,y,z));

                            BlockState x1_z2_state = pContext.getLevel().getBlockState(positionClicked.offset(x,y,-z));
                            BlockState x2_z2_state = pContext.getLevel().getBlockState(positionClicked.offset(-x,y,-z));

                            if(isGrassBlock(x1_z1_state))
                            {
                                outputDirtVariables(positionClicked.offset(x,y,z), player, x1_z1_state.getBlock());
                            }
                            if(isGrassBlock(x2_z1_state))
                            {
                                outputDirtVariables(positionClicked.offset(-x,y,z), player, x2_z1_state.getBlock());
                            }
                            if(isGrassBlock(x1_z2_state))
                            {
                                outputDirtVariables(positionClicked.offset(x,y,-z), player, x1_z2_state.getBlock());
                            }
                            if(isGrassBlock(x2_z2_state))
                            {
                                outputDirtVariables(positionClicked.offset(-x,y,-z), player, x2_z2_state.getBlock());
                            }
                        }
                        else if(x != 0)
                        {
                            BlockState x1_state = pContext.getLevel().getBlockState(positionClicked.offset(x,y,0));
                            BlockState x2_state = pContext.getLevel().getBlockState(positionClicked.offset(-x,y,0));

                            if(isGrassBlock(x1_state))
                            {
                                outputDirtVariables(positionClicked.offset(x,y,0), player, x1_state.getBlock());
                            }
                            if(isGrassBlock(x2_state))
                            {
                                outputDirtVariables(positionClicked.offset(-x,y,0), player, x2_state.getBlock());
                            }
                        }
                        else if(z != 0)
                        {
                            BlockState z1_state = pContext.getLevel().getBlockState(positionClicked.offset(0,y,z));
                            BlockState z2_state = pContext.getLevel().getBlockState(positionClicked.offset(0,y,-z));

                            if(isGrassBlock(z1_state))
                            {
                                outputDirtVariables(positionClicked.offset(0,y,z), player, z1_state.getBlock());
                            }
                            if(isGrassBlock(z1_state))
                            {
                                outputDirtVariables(positionClicked.offset(0,y,-z), player, z1_state.getBlock());
                            }
                        }

                        z -= 1;
                    } while(z >= 0);

                    x -= 1;
                } while(x >= 0);
            }
        }

        // Damage the item that is used
        pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputDirtVariables(BlockPos blockPos, Player player, Block block)
    {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private Boolean isGrassBlock(BlockState state)
    {
        return state.is(Blocks.GRASS_BLOCK);
    }
}
