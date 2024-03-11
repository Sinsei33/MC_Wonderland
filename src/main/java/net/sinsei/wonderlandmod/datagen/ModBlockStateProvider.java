package net.sinsei.wonderlandmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
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
        //blockWithItem(ModBlocks.BISCUIT_BLOCK);
        blockWithItem(ModBlocks.CHOCOLATE_BLOCK);

        stairsBlock((StairBlock) ModBlocks.BISCUIT_STAIRS.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));
        slabBlock((SlabBlock) ModBlocks.BISCUIT_SLAB.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));

        buttonBlock((ButtonBlock) ModBlocks.BISCUIT_BUTTON.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.BISCUIT_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));

        fenceBlock((FenceBlock) ModBlocks.BISCUIT_FENCE.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.BISCUIT_FENCE_GATE.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));
        wallBlock((WallBlock) ModBlocks.BISCUIT_WALL.get(), blockTexture(ModBlocks.BISCUIT_BLOCK.get()));

        doorBlockWithRenderType((DoorBlock) ModBlocks.BISCUIT_DOOR.get(), modLoc("block/biscuit_door_bottom"), modLoc("block/biscuit_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.BISCUIT_TRAPDOOR.get(), modLoc("block/biscuit_trapdoor"), true, "cutout");

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
