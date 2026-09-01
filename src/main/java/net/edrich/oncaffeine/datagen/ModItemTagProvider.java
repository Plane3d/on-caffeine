package net.edrich.oncaffeine.datagen;

import net.edrich.oncaffeine.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.HERBAL_TEA_CRAFTABLE)
                .add(Items.ALLIUM)
                .add(Items.BLUE_ORCHID)
                .add(Items.CORNFLOWER)
                .add(Items.DANDELION)
                .add(Items.GLOW_BERRIES)
                .add(Items.OXEYE_DAISY)
                .add(Items.POPPY)
                .add(Items.ROSE_BUSH)
                .add(Items.SWEET_BERRIES)
                .add(Items.TORCHFLOWER);
    }
}
