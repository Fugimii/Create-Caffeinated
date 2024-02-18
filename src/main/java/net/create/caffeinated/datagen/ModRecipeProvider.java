package net.create.caffeinated.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.create.caffeinated.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> TEA_LEAF_SMELTABLES = List.of(ModItems.TEA_LEAVES);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.TEA_CUP)
                .pattern("   ")
                .pattern("Q Q")
                .pattern("QQQ")
                .input('Q', Items.QUARTZ)
                .criterion(FabricRecipeProvider.hasItem(Items.QUARTZ),
                        FabricRecipeProvider.conditionsFromItem(Items.QUARTZ))
                .offerTo(exporter);
    }
}