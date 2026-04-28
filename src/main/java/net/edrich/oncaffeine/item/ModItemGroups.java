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
        entries.add(ModBlocks.MUG_BLACK_TEA);
        entries.add(ModBlocks.MUG_HERBAL_TEA);
        entries.add(ModBlocks.MUG_GREEN_TEA);
        entries.add(ModBlocks.MUG_HOT_WATER);

        entries.add(ModItems.TEA_SEEDS);
        entries.add(ModItems.TEA_LEAVES);

        entries.add(ModItems.COFFEE_FRUIT);

        entries.add(ModItems.BLACK_TEA_LEAVES);
        entries.add(ModItems.GREEN_TEA_LEAVES);
        entries.add(ModItems.BLENDED_HERBS);
        entries.add(ModItems.RAW_COFFEE_BEANS);
        entries.add(ModItems.COFFEE_BEANS);

        entries.add(ModBlocks.WILD_COFFEE);
        entries.add(ModBlocks.WILD_TEA);

        entries.add(ModBlocks.CLASSIC_COFFEE_MACHINE);
        entries.add(ModBlocks.CLASSIC_TEA_KETTLE);
        entries.add(ModBlocks.DRYING_TABLE);

    }).build());
    public static void registerItemGroups(){
        OnCaffeine.LOGGER.info("Registering Item Groups for" + OnCaffeine.MOD_ID);
    }
}
