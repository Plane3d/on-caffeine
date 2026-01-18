package net.edrich.oncaffeine.item;

import net.edrich.oncaffeine.OnCaffeine;
import net.edrich.oncaffeine.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup COFFEE_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(OnCaffeine.MOD_ID, "coffee"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.oncaffeine")).icon(() -> new ItemStack(ModBlocks.MUG_EMPTY)).entries((displayContext, entries) -> {
        entries.add(ModBlocks.MUG_EMPTY);
        entries.add(ModBlocks.MUG_COFFEE);

    }).build());
    public static void registerItemGroups(){
        OnCaffeine.LOGGER.info("Registering Item Groups for" + OnCaffeine.MOD_ID);
    }
}
