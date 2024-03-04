package net.sinsei.wonderlandmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, WonderlandMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        blockWithItem(ModBlocks.BLOCK_CHANGE_BLOCK);
        blockWithItem(ModBlocks.CHOCOLATE_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
