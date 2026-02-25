package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.CoffeeCropBlock;
import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.block.TeaCropBlock;
import net.edrich.oncaffeine.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.TEA_CROP, TeaCropBlock.AGE, 0,1,2,3,4,5);
        blockStateModelGenerator.registerCrop(ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE, 0,1,2,3,4,5,6,7,8);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TEA_LEAVES, Models.GENERATED);
    }
}
