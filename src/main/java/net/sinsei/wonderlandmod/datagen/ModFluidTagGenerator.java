package net.sinsei.wonderlandmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.ModFluids;
import net.sinsei.wonderlandmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagGenerator extends FluidTagsProvider
{
    public ModFluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, WonderlandMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        this.tag(ModTags.Fluids.HONEY_TAG)
                .add(ModFluids.HONEY_FLUID_FLOW.get())
                .add(ModFluids.HONEY_FLUID_SOURCE.get());

        this.tag(ModTags.Fluids.SWEETS_TAG)
                .add(ModFluids.LIQUID_STARLIGHT_FLUID_FLOW.get())
                .add(ModFluids.LIQUID_STARLIGHT_FLUID_SOURCE.get());
    }
}
