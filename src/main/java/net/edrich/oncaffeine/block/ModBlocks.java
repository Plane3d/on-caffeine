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
    public static final Block MUG_EMPTY = registerBlock("mug_empty", new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()));
    public static final Block MUG_COFFEE = registerBeverageBlock("mug_coffee",
            new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()), ModFoodComponents.COFFEE_MEDIUM);
    public static final Block MUG_GREEN_TEA = registerBeverageBlock("mug_green_tea",
            new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()), ModFoodComponents.GREEN_MEDIUM);
    public static final Block MUG_BLACK_TEA = registerBeverageBlock("mug_black_tea",
            new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()), ModFoodComponents.BLACK_MEDIUM);
    public static final Block MUG_HERBAL_TEA = registerBeverageBlock("mug_herbal_tea",
            new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()), ModFoodComponents.HERBAL_MEDIUM);
    public static final Block MUG_HOT_WATER = registerBlock("mug_hot_water",
            new BeverageBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).nonOpaque()));

    public static final Block TEA_CROP = Registry.register(Registries.BLOCK, new Identifier(OnCaffeine.MOD_ID, "tea_crop"),
            new TeaCropBlock(FabricBlockSettings.copyOf(Blocks.BEETROOTS)));
    public static final Block COFFEE_CROP = Registry.register(Registries.BLOCK, new Identifier(OnCaffeine.MOD_ID, "coffee_crop"),
            new CoffeeCropBlock(FabricBlockSettings.copyOf(Blocks.CARROTS)));

    public static final Block WILD_COFFEE = registerBlock("wild_coffee",
            new VegetationBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS)));
    public static final Block WILD_TEA = registerBlock("wild_tea",
            new VegetationBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS)));

    public static final Block CLASSIC_COFFEE_MACHINE = registerBlock("classic_coffee_machine",
            new ClassicCoffeeMachineBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block CLASSIC_TEA_KETTLE = registerBlock("classic_tea_kettle",
            new ClassicTeaKettleBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block DRYING_TABLE = registerBlock("drying_table",
            new DryingTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

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
    private static Item registerBeverageItem(String name, Block block, FoodComponent foodComponent) {
        return Registry.register(Registries.ITEM, new Identifier(OnCaffeine.MOD_ID, name),
                new BeverageBlockItem(block, new FabricItemSettings().food(foodComponent).maxCount(1)));
    }

    public static void registerModBlocks(){
        OnCaffeine.LOGGER.info("Registering Mod Blocks for " + OnCaffeine.MOD_ID);
    }
}
