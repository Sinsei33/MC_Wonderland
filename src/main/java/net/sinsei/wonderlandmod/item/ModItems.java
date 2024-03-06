package net.sinsei.wonderlandmod.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.Blocks.ModBlocks;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.fluid.ModFluids;
import net.sinsei.wonderlandmod.item.Custom.BlockChangeItem;
import net.sinsei.wonderlandmod.item.Custom.FuelItem;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WonderlandMod.MOD_ID);

    public static final RegistryObject<Item> BLOCK_CHANGE_ITEM = ITEMS.register("block_change_item", () -> new BlockChangeItem(new Item.Properties().durability(3)));

    public static final RegistryObject<Item> CAKE_SEEDS_ITEM = ITEMS.register("cake_seeds_item",
            () -> new ItemNameBlockItem(ModBlocks.CAKE_CROP_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHOCOLATE_ITEM = ITEMS.register("chocolate_item", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_ITEM)));
    public static final RegistryObject<Item> LOLLY_ITEM = ITEMS.register("lolly_item", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BURN_ITEM = ITEMS.register("burn_item", () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket", () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> HONEY_BUCKET = ITEMS.register("honey_bucket", () -> new BucketItem(ModFluids.HONEY_FLUID_SOURCE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LIQUID_STARLIGHT_BUCKET = ITEMS.register("liquid_starlight_bucket", () -> new BucketItem(ModFluids.LIQUID_STARLIGHT_FLUID_SOURCE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));



    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
