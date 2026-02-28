package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.CoffeeCropBlock;
import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.block.TeaCropBlock;
import net.edrich.oncaffeine.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.TEA_CROP, TeaCropBlock.AGE, 0,1,2,3,4,5);
        blockStateModelGenerator.registerCrop(ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE, 0,1,2,3,4,5,6,7,8);

        blockStateModelGenerator.registerSimpleState(ModBlocks.CLASSIC_COFFEE_MACHINE);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLENDED_HERBS, Models.GENERATED);

    }
}
