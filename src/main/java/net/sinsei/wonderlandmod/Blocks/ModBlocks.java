package net.sinsei.wonderlandmod.Blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.Custom.BlockChangeBlock;
import net.sinsei.wonderlandmod.Blocks.Custom.CakeCropBlock;
import net.sinsei.wonderlandmod.Blocks.Custom.HoneyFluidBlock;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.ModFluids;
import net.sinsei.wonderlandmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WonderlandMod.MOD_ID);

    public static final RegistryObject<Block> BLOCK_CHANGE_BLOCK = registerBlock("block_change_block",
            () -> new BlockChangeBlock(BlockBehaviour.Properties.copy(Blocks.HONEYCOMB_BLOCK)
                    .requiresCorrectToolForDrops().strength(1.0F, 2.0F)));

    public static final RegistryObject<Block> CAKE_CROP_BLOCK = registerBlock("cake_crop_block",
            () -> new CakeCropBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> ATTACHED_CAKE_CROP_STEM = registerBlock("attached_cake_crop_stem",
            () -> new AttachedStemBlock((StemGrownBlock) CAKE_CROP_BLOCK.get(),
                    ModItems.CAKE_SEEDS_ITEM,
                    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).noLootTable()));
    public static final RegistryObject<Block> CAKE_CROP_STEM = registerBlock("cake_crop_stem",
            () -> new StemBlock((StemGrownBlock) CAKE_CROP_BLOCK.get(),
                    ModItems.CAKE_SEEDS_ITEM,
                    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP).pushReaction(PushReaction.DESTROY).noLootTable()));

    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<LiquidBlock> HONEY_BLOCK = BLOCKS.register("honey_block",
            () -> new LiquidBlock(ModFluids.HONEY_FLUID_SOURCE, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<LiquidBlock> LIQUID_STARLIGHT_BLOCK = BLOCKS.register("liquid_starlight_block",
            () -> new LiquidBlock(ModFluids.LIQUID_STARLIGHT_FLUID_SOURCE, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<Block> CHOCOLATE_BLOCK = registerBlock("chocolate_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HONEYCOMB_BLOCK)
                    .requiresCorrectToolForDrops().strength(1.0F, 2.0F)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
