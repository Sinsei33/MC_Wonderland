package net.sinsei.wonderlandmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.item.Custom.BlockChangeItem;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WonderlandMod.MOD_ID);

    public static final RegistryObject<Item> BLOCK_CHANGE_ITEM = ITEMS.register("block_change_item", () -> new BlockChangeItem(new Item.Properties().durability(3)));

    public static final RegistryObject<Item> CAKE_SEEDS_ITEM = ITEMS.register("cake_seeds_item",
            () -> new ItemNameBlockItem(ModBlocks.CAKE_CROP_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHOCOLATE_ITEM = ITEMS.register("chocolate_item", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_ITEM)));
    public static final RegistryObject<Item> LOLLY_ITEM = ITEMS.register("lolly_item", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
