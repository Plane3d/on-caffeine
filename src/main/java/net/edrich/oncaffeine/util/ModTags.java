package net.edrich.oncaffeine.util;

import net.edrich.oncaffeine.OnCaffeine;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks
    {
        public static final TagKey<Block> CUP_BLOCKS_UNPLACEABLE = createTag("cup_blocks_unplaceable");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(OnCaffeine.MOD_ID, name));
        }
    }

    public static class Items
    {
        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(OnCaffeine.MOD_ID, name));
        }
    }
}
