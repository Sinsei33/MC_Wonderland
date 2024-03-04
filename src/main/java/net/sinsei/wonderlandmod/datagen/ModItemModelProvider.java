package net.sinsei.wonderlandmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.sinsei.wonderlandmod.WonderlandMod;
import net.sinsei.wonderlandmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider
{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, WonderlandMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        simpleItem(ModItems.BLOCK_CHANGE_ITEM);
        //simpleItem(ModItems.BURN_ITEM);
        simpleItem(ModItems.CAKE_SEEDS_ITEM);
        simpleItem(ModItems.CHOCOLATE_ITEM);
        //simpleItem(ModItems.LOLLY_ITEM);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WonderlandMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
