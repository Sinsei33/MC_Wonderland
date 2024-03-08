package net.sinsei.wonderlandmod.Blocks.Custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class SweetDirtBlock extends Block
{
    public static final int RADIUS = 6;

    public SweetDirtBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
    {
        if(toolAction.equals(ToolAction.get("till")))
            context.getLevel().setBlock(context.getClickedPos(), ModBlocks.SWEET_FARM_BLOCK.get().defaultBlockState(), 3);
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}

