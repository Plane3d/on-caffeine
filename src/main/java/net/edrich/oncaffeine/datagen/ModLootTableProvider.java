package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

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
    }
}
