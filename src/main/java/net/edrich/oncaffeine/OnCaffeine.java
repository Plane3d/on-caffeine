package net.edrich.oncaffeine;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.block.entity.ModBlockEntities;
import net.edrich.oncaffeine.item.ModItemGroups;
import net.edrich.oncaffeine.item.ModItems;
import net.edrich.oncaffeine.screen.ModScreenHandlers;
import net.edrich.oncaffeine.worldgen.ModPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnCaffeine implements ModInitializer {
	public static final String MOD_ID = "oncaffeine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandler();
		LOGGER.info("A cup of hot coffee is on the way!");

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.WILD_COFFEE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.WILD_TEA_PLACED_KEY
        );
	}
}