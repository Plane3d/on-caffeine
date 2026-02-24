package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.block.TeaCropBlock;
import net.edrich.oncaffeine.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
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

        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition
                .builder(ModBlocks.TEA_CROP)
                .properties(StatePredicate.Builder.create()
                .exactMatch(TeaCropBlock.AGE, 5));
        addDrop(ModBlocks.TEA_CROP, cropDrops(ModBlocks.TEA_CROP, ModItems.TEA_LEAVES, ModItems.TEA_SEEDS, builder));
        /* change tea crop generated to lower prob for seed and raise for leave */
    }
}
