package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.item.ModItems;
import net.edrich.oncaffeine.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> COFFEE_ROASTABLE = List.of(ModItems.RAW_COFFEE_BEANS);
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, COFFEE_ROASTABLE, RecipeCategory.FOOD,
                ModItems.COFFEE_BEANS, 0.7f,
                200, "coffee");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLASSIC_COFFEE_MACHINE, 1)
                .pattern("ISI")
                .pattern("GSG")
                .pattern("CCC")
                .input('C', Blocks.COBBLESTONE)
                .input('G', Blocks.GLASS)
                .input('S', Items.STICK)
                .input('I',Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModBlocks.MUG_EMPTY), conditionsFromItem(ModBlocks.MUG_EMPTY))
                .criterion(hasItem(ModItems.COFFEE_FRUIT), conditionsFromItem(ModItems.COFFEE_FRUIT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CLASSIC_COFFEE_MACHINE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLASSIC_TEA_KETTLE, 1)
                .pattern("SII")
                .pattern("S G")
                .pattern("CCC")
                .input('C', Blocks.COBBLESTONE)
                .input('G', Blocks.GLASS)
                .input('S', Items.STICK)
                .input('I',Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModBlocks.MUG_EMPTY), conditionsFromItem(ModBlocks.MUG_EMPTY))
                .criterion(hasItem(ModItems.TEA_SEEDS), conditionsFromItem(ModItems.TEA_SEEDS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CLASSIC_TEA_KETTLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DRYING_TABLE, 1)
                .pattern("SIS")
                .pattern("S S")
                .input('S', Items.STICK)
                .input('I',Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.COFFEE_FRUIT), conditionsFromItem(ModItems.COFFEE_FRUIT))
                .criterion(hasItem(ModItems.TEA_LEAVES), conditionsFromItem(ModItems.TEA_LEAVES))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.DRYING_TABLE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MUG_EMPTY, 2)
                .pattern("B B")
                .pattern("BBB")
                .input('B', Items.BRICK)
                .criterion(hasItem(Items.BRICK), conditionsFromItem(Items.BRICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MUG_EMPTY)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BLENDED_HERBS, 1)
                .input(ModTags.Items.HERBAL_TEA_CRAFTABLE)
                .input(ModTags.Items.HERBAL_TEA_CRAFTABLE)
                .input(ModTags.Items.HERBAL_TEA_CRAFTABLE)
                .criterion(hasItem(Items.DANDELION), conditionsFromItem(Items.DANDELION))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BLENDED_HERBS)));




    }
}