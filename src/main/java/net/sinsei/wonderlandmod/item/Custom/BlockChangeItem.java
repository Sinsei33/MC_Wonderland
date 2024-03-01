package net.sinsei.wonderlandmod.item.Custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;

public class BlockChangeItem extends Item
{
    public static final int RADIUS = 6;

    public BlockChangeItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level world = pContext.getLevel();
        if(!world.isClientSide())
        {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean blocks_found = false;

            if(player != null) {
                for (int x = RADIUS; x >= -RADIUS; x -= 1) {
                    for (int y = RADIUS; y >= -RADIUS; y -= 1) {
                        for (int z = RADIUS; z >= -RADIUS; z -= 1) {
                            if ((Math.abs(x * x) + Math.abs(y * y) + Math.abs(z * z)) <= (RADIUS * RADIUS) + RADIUS) {
                                BlockPos pos = positionClicked.offset(x, y, z);
                                BlockState state = pContext.getLevel().getBlockState(pos);
                                if (isGrassBlock(state)) {
                                    outputDirtVariables(pos, player, state.getBlock());
                                    world.setBlock(pos, ModBlocks.CHOCOLATE_BLOCK.get().defaultBlockState(), 3);

                                    blocks_found = true;
                                }
                            }
                        }
                    }
                }

                if(blocks_found)
                    // Damage the item that is used
                    pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(), play -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }

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
