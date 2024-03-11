package net.sinsei.wonderlandmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;

import java.awt.*;

public class ModCreativeModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WonderlandMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WONDERLAND_TAB = CREATIVE_MODE_TABS.register("wonderland_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LOLLY_ITEM.get()))
                .title(Component.translatable("creativetab.wonderland_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BLOCK_CHANGE_ITEM.get());
                        pOutput.accept(ModItems.CAKE_SEEDS_ITEM.get());
                        pOutput.accept(ModItems.CHOCOLATE_ITEM.get());
                        pOutput.accept(ModItems.LOLLY_ITEM.get());
                        pOutput.accept(ModItems.BURN_ITEM.get());
                        pOutput.accept(ModItems.SOAP_WATER_BUCKET.get());
                        pOutput.accept(ModItems.HONEY_BUCKET.get());
                        pOutput.accept(ModItems.LIQUID_SUGAR_BUCKET.get());

                        pOutput.accept(ModBlocks.BLOCK_CHANGE_BLOCK.get());
                        pOutput.accept(ModBlocks.CAKE_CROP_BLOCK.get());
                        pOutput.accept(ModBlocks.CHOCOLATE_BLOCK.get());
                        pOutput.accept(ModBlocks.SWEET_DIRT_BLOCK.get());
                        pOutput.accept(ModBlocks.SWEET_GRASS_BLOCK.get());
                        pOutput.accept(ModBlocks.SWEET_FARM_BLOCK.get());
                        //pOutput.accept(ModBlocks.SOAP_WATER_BLOCK.get());

                        pOutput.accept(ModBlocks.BISCUIT_BLOCK.get());
                        pOutput.accept(ModBlocks.BISCUIT_DOOR.get());
                        pOutput.accept(ModBlocks.BISCUIT_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.BISCUIT_FENCE.get());
                        pOutput.accept(ModBlocks.BISCUIT_WALL.get());
                        pOutput.accept(ModBlocks.BISCUIT_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.BISCUIT_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.BISCUIT_BUTTON.get());
                        pOutput.accept(ModBlocks.BISCUIT_SLAB.get());
                        pOutput.accept(ModBlocks.BISCUIT_STAIRS.get());
                    })
                    .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
