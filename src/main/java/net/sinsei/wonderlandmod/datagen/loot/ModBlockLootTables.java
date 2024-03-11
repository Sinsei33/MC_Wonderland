package net.sinsei.wonderlandmod.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider
{
    protected LootTable.Builder createMultipleDrops(Block pBlock, Item pItem, float minCount, float maxCount) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(ModItems.CHOCOLATE_ITEM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)))));
    }

    public ModBlockLootTables()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        this.dropSelf(ModBlocks.BLOCK_CHANGE_BLOCK.get());
        this.dropSelf(ModBlocks.BISCUIT_BLOCK.get());
        this.dropSelf(ModBlocks.CAKE_CROP_BLOCK.get());
        this.dropSelf(ModBlocks.SWEET_DIRT_BLOCK.get());

        this.dropSelf(ModBlocks.BISCUIT_STAIRS.get());
        this.dropSelf(ModBlocks.BISCUIT_BUTTON.get());
        this.dropSelf(ModBlocks.BISCUIT_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.BISCUIT_TRAPDOOR.get());
        this.dropSelf(ModBlocks.BISCUIT_FENCE.get());
        this.dropSelf(ModBlocks.BISCUIT_FENCE_GATE.get());
        this.dropSelf(ModBlocks.BISCUIT_WALL.get());

        this.add(ModBlocks.BISCUIT_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BISCUIT_SLAB.get()));
        this.add(ModBlocks.BISCUIT_DOOR.get(),
                block -> createDoorTable(ModBlocks.BISCUIT_DOOR.get()));

        // Todo Silk Touch
        this.dropOther(ModBlocks.SWEET_GRASS_BLOCK.get(), ModBlocks.SWEET_DIRT_BLOCK.get());
        this.dropOther(ModBlocks.SWEET_FARM_BLOCK.get(), ModBlocks.SWEET_DIRT_BLOCK.get());

        this.add(ModBlocks.CHOCOLATE_BLOCK.get(),
                block -> createMultipleDrops(ModBlocks.CHOCOLATE_BLOCK.get(), ModItems.CHOCOLATE_ITEM.get(), 1, 3));

    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
