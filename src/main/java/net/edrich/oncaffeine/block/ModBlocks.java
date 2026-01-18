package net.edrich.oncaffeine.block;

import net.edrich.oncaffeine.OnCaffeine;
import net.edrich.oncaffeine.item.ModFoodComponents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block MUG_EMPTY = registerBlock("mug_empty", new Block (FabricBlockSettings.copyOf(Blocks.DECORATED_POT)));
    public static final Block MUG_COFFEE = registerBeverageBlock("mug_coffee", new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()), ModFoodComponents.COFFEE_MEDIUM);

    public static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(OnCaffeine.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(OnCaffeine.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    private static Block registerBeverageBlock(String name, Block block, FoodComponent foodComponent) {
        registerBeverageItem(name,block,foodComponent);
        return Registry.register(Registries.BLOCK, new Identifier(OnCaffeine.MOD_ID, name), block);
    }
    private static Item registerBeverageItem(String name, Block block, FoodComponent foodComponent){
        return Registry.register(Registries.ITEM, new Identifier(OnCaffeine.MOD_ID, name),
                new BeverageBlockItem(block, new FabricItemSettings().food(foodComponent).maxCount(1)));
    }
    public static void registerModBlocks(){
        OnCaffeine.LOGGER.info("Registering Mod Blocks for " + OnCaffeine.MOD_ID);
    }
}
