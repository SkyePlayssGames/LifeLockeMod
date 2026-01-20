package com.galaxyy.lifelocke.item;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class CraftingRecipeGenerator extends FabricRecipeProvider {
    public CraftingRecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                shapeless(RecipeCategory.BREWING, ModItems.FIRE_BOTTLES.get("burst"))
                        .requires(ModItems.FIRE_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.FIRE_ENERGY), has(ModItems.FIRE_ENERGY))
                        .save(output);

                shapeless(RecipeCategory.BREWING, ModItems.GRASS_BOTTLES.get("burst"))
                        .requires(ModItems.GRASS_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.GRASS_ENERGY), has(ModItems.GRASS_ENERGY))
                        .save(output);

                shapeless(RecipeCategory.BREWING, ModItems.GHOST_BOTTLES.get("burst"))
                        .requires(ModItems.GHOST_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.GHOST_ENERGY), has(ModItems.GHOST_ENERGY))
                        .save(output);

            }
        };
    }

    @Override
    public String getName() {
        return "LifeLockeCraftingRecipeProvider";
    }
}
