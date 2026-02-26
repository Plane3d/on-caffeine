package net.edrich.oncaffeine.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class BrewableItem extends Item {

    public BrewableItem(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        if (false)
        {

        }

        return ActionResult.PASS;
    }


}
