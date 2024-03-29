package net.sinsei.wonderlandmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.sinsei.wonderlandmod.WonderlandMod;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> BLOCK_CHANGE_ITEM_POSS = tag("block_change_item_poss");


        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(WonderlandMod.MOD_ID, name));
        }
    }

    public static class Items
    {
        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(WonderlandMod.MOD_ID, name));
        }
    }

    public static class Fluids
    {
        public static final TagKey<Fluid> HONEY_FLUID_TAG = FluidTags.create(new ResourceLocation("forge", "honey"));
        public static final TagKey<Fluid> SWEETS_FLUID_TAG = FluidTags.create(new ResourceLocation("forge", "sweets"));

        private static TagKey<Fluid> tag(String name)
        {
            return FluidTags.create(new ResourceLocation(WonderlandMod.MOD_ID, name));
        }
    }
}
