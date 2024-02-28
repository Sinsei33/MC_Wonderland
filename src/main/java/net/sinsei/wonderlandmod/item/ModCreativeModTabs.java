package net.sinsei.wonderlandmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.WonderlandMod;

import java.awt.*;

public class ModCreativeModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WonderlandMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WONDERLAND_TAB = CREATIVE_MODE_TABS.register("wonderland_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LOLLY.get()))
                .title(Component.translatable("creativetab.wonderland_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept((ModItems.CAKE_SEEDS.get()));
                        pOutput.accept((ModItems.LOLLY.get()));
                    })
                    .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
