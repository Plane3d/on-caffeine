package net.edrich.oncaffeine.item;

import net.edrich.oncaffeine.OnCaffeine;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(OnCaffeine.MOD_ID, name), item);
    }
    public static void registerModItems(){
        OnCaffeine.LOGGER.info("Registering Mod Items for "+ OnCaffeine.MOD_ID);

    }
}
