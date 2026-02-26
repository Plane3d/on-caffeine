package net.edrich.oncaffeine.item;

import net.edrich.oncaffeine.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;



public class BrewableItem extends Item {

    public BrewableItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        BlockPos position = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = context.getWorld().getBlockState(position);

        if (isHotWater(state))
        {
            if (context.getStack().isOf(ModItems.BLACK_TEA_LEAVES))
            {
                convertCup(ModBlocks.MUG_BLACK_TEA.getDefaultState(), context, position);
            }
            else if (context.getStack().isOf(ModItems.GREEN_TEA_LEAVES))
            {
                convertCup(ModBlocks.MUG_GREEN_TEA.getDefaultState(), context, position);
            }
            else if (context.getStack().isOf(ModItems.BLENDED_HERBS))
            {
                convertCup(ModBlocks.MUG_HERBAL_TEA.getDefaultState(), context, position);
            }
        }

        return ActionResult.PASS;
    }

    private static void convertCup(BlockState blockState, ItemUsageContext context, BlockPos position)
    {
        context.getWorld().setBlockState(position, blockState);
        context.getWorld().playSound((PlayerEntity)null, position, SoundEvents.BLOCK_FIRE_EXTINGUISH,
                SoundCategory.BLOCKS, 1.0F,
                (1.0F + context.getWorld().getRandom().nextFloat() * 0.2F) * 0.7F);
        context.getWorld().addParticle(ParticleTypes.ASH, position.getX(), position.getY(), position.getZ(),
                0.0, 0.0, 0.0);
    }


    private boolean isHotWater(BlockState state)
    {
        return state.isOf(ModBlocks.MUG_HOT_WATER);
    }


}
