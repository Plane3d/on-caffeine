package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.CoffeeCropBlock;
import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.block.TeaCropBlock;
import net.edrich.oncaffeine.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.MUG_EMPTY);
        addDrop(ModBlocks.MUG_COFFEE);
        addDrop(ModBlocks.MUG_BLACK_TEA);
        addDrop(ModBlocks.MUG_GREEN_TEA);
        addDrop(ModBlocks.MUG_HERBAL_TEA);
        addDrop(ModBlocks.MUG_HOT_WATER);

        BlockStatePropertyLootCondition.Builder builderTea = BlockStatePropertyLootCondition
                .builder(ModBlocks.TEA_CROP)
                .properties(StatePredicate.Builder.create()
                .exactMatch(TeaCropBlock.AGE, 5));
        addDrop(ModBlocks.TEA_CROP, cropDrops(ModBlocks.TEA_CROP, ModItems.TEA_LEAVES, ModItems.TEA_SEEDS, builderTea));
        /* change tea crop generated to lower prob for seed and raise for leave */

        AnyOfLootCondition.Builder builderCoffee = BlockStatePropertyLootCondition.builder(ModBlocks.COFFEE_CROP).properties(StatePredicate.Builder.create()
                        .exactMatch(CoffeeCropBlock.AGE, 7))
                .or(BlockStatePropertyLootCondition.builder(ModBlocks.COFFEE_CROP).properties(StatePredicate.Builder.create()
                        .exactMatch(CoffeeCropBlock.AGE,8)));
        addDrop(ModBlocks.COFFEE_CROP, cropDrops(ModBlocks.COFFEE_CROP, ModItems.COFFEE_FRUIT, ModItems.COFFEE_FRUIT, builderCoffee));
    }
}
