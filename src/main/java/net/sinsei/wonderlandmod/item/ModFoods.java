package net.sinsei.wonderlandmod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods
{
    public static final FoodProperties CHOCOLATE_ITEM = new FoodProperties.Builder()
            .nutrition(5).saturationMod(2).fast()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 200), 1f)
            .build();
}
