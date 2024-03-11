package net.sinsei.wonderlandmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.ModFluids;
import net.sinsei.wonderlandmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider
{
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, WonderlandMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        this.tag(ModTags.Blocks.BLOCK_CHANGE_ITEM_POSS)
                .add(Blocks.GRASS_BLOCK);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISCUIT_BLOCK.get())
                .add(ModBlocks.CHOCOLATE_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.CHOCOLATE_BLOCK.get())
                .add(ModBlocks.SWEET_GRASS_BLOCK.get())
                .add(ModBlocks.SWEET_DIRT_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BISCUIT_BLOCK.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.BISCUIT_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BISCUIT_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.BISCUIT_WALL.get());
    }
}
