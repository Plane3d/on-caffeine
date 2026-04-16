package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {




    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.CUP_BLOCKS_UNPLACEABLE)
                .add(ModBlocks.MUG_EMPTY)
                .add(ModBlocks.MUG_COFFEE)
                .add(ModBlocks.MUG_BLACK_TEA)
                .add(ModBlocks.MUG_GREEN_TEA)
                .add(ModBlocks.MUG_HERBAL_TEA)
                .add(ModBlocks.MUG_HOT_WATER)
                .add(Blocks.AIR);

        getOrCreateTagBuilder(ModTags.Blocks.CAFFEINE_FOLIAGE)
                .add(ModBlocks.WILD_COFFEE)
                .add(ModBlocks.WILD_TEA)
                .add(Blocks.AIR);
    }
}
