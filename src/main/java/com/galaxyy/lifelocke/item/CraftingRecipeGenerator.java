package com.galaxyy.lifelocke.item;

import com.galaxyy.lifelocke.effect.Types;
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
                assert Types.FIRE_TYPE.bottles.isPresent();
                assert Types.GRASS_TYPE.bottles.isPresent();
                assert Types.GHOST_TYPE.bottles.isPresent();
                assert Types.PSYCHIC_TYPE.bottles.isPresent();

                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                shapeless(RecipeCategory.BREWING, Types.FIRE_TYPE.bottles.get().burst)
                        .requires(ModItems.FIRE_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.FIRE_ENERGY), has(ModItems.FIRE_ENERGY))
                        .save(output);
                shapeless(RecipeCategory.BREWING, Types.GRASS_TYPE.bottles.get().burst)
                        .requires(ModItems.GRASS_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.GRASS_ENERGY), has(ModItems.GRASS_ENERGY))
                        .save(output);
                shapeless(RecipeCategory.BREWING, Types.GHOST_TYPE.bottles.get().burst)
                        .requires(ModItems.GHOST_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.GHOST_ENERGY), has(ModItems.GHOST_ENERGY))
                        .save(output);
                shapeless(RecipeCategory.BREWING, Types.PSYCHIC_TYPE.bottles.get().burst)
                        .requires(ModItems.PSYCHIC_ENERGY)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModItems.PSYCHIC_ENERGY), has(ModItems.PSYCHIC_ENERGY))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "LifeLockeCraftingRecipeProvider";
    }
}
