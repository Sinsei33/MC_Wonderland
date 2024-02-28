package net.sinsei.wonderlandmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.WonderlandMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WonderlandMod.MOD_ID);

    public static final RegistryObject<Item> CAKE_SEEDS = ITEMS.register("cake_seeds",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOLLY = ITEMS.register("lolly",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
