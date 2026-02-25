package net.edrich.oncaffeine.item;

import net.edrich.oncaffeine.OnCaffeine;
import net.edrich.oncaffeine.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item TEA_LEAVES = registerItem("tea_leaves", new Item(new FabricItemSettings()));
    public static final Item TEA_SEEDS = registerItem("tea_seeds", new AliasedBlockItem(ModBlocks.TEA_CROP, new FabricItemSettings()));

    public static final Item COFFEE_FRUIT = registerItem("coffee_fruit", new AliasedBlockItem(ModBlocks.COFFEE_CROP, new FabricItemSettings()));
    private static Item registerItem(String name, Item item){

        return Registry.register(Registries.ITEM, new Identifier(OnCaffeine.MOD_ID, name), item);
    }


    public static void registerModItems(){
        OnCaffeine.LOGGER.info("Registering Mod Items for "+ OnCaffeine.MOD_ID);

    }
}
