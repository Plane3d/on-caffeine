package net.edrich.oncaffeine;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.block.entity.ModBlockEntities;
import net.edrich.oncaffeine.item.ModItemGroups;
import net.edrich.oncaffeine.item.ModItems;
import net.edrich.oncaffeine.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

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
	}
}