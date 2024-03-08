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
import net.sinsei.wonderlandmod.Blocks.Custom.*;
import net.sinsei.wonderlandmod.Blocks.Blocktypes.SweetAttachedStemBlock;
import net.sinsei.wonderlandmod.Blocks.Blocktypes.SweetStemBlock;
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

    public static final RegistryObject<Block> SWEET_DIRT_BLOCK = registerBlock("sweet_dirt_block",
            () -> new SweetDirtBlock(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.ROOTED_DIRT)));
    public static final RegistryObject<Block> SWEET_GRASS_BLOCK = registerBlock("sweet_grass_block",
            () -> new SweetGrassBlock(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.ROOTED_DIRT).randomTicks()));
    public static final RegistryObject<Block> SWEET_FARM_BLOCK = registerBlock("sweet_farm_block",
            () -> new SweetFarmBlock(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.ROOTED_DIRT).randomTicks()));

    public static final RegistryObject<Block> CAKE_CROP_BLOCK = registerBlock("cake_crop_block",
            () -> new CakeCropBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> ATTACHED_CAKE_CROP_STEM = registerBlock("attached_cake_crop_stem",
            () -> new SweetAttachedStemBlock((StemGrownBlock) CAKE_CROP_BLOCK.get(),
                    ModItems.CAKE_SEEDS_ITEM,
                    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).noLootTable()));
    public static final RegistryObject<Block> CAKE_CROP_STEM = registerBlock("cake_crop_stem",
            () -> new SweetStemBlock((StemGrownBlock) CAKE_CROP_BLOCK.get(),
                    ModItems.CAKE_SEEDS_ITEM,
                    BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP).pushReaction(PushReaction.DESTROY).noLootTable()));

    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<LiquidBlock> HONEY_BLOCK = BLOCKS.register("honey_block",
            () -> new HoneyFluidBlock(ModFluids.HONEY_FLUID_SOURCE, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<LiquidBlock> LIQUID_SUGAR_BLOCK = BLOCKS.register("liquid_sugar_block",
            () -> new LiquidBlock(ModFluids.LIQUID_SUGAR_FLUID_SOURCE, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

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
