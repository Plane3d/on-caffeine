package net.edrich.oncaffeine.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class CoffeeCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 5;
    public static final int SECOND_STAGE_MAX_STAGE = 3;


    public static final IntProperty AGE = IntProperty.of("age", 0, 8);

    public CoffeeCropBlock(Settings settings) {
        super(settings);
    }


    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random){
        if (world.getBaseLightLevel(pos, 0) >= 9)
        {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge())
            {
                float num = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0f / num)+1) == 0)
                {
                    if (currentAge == FIRST_STAGE_MAX_AGE)
                    {
                        if(world.getBlockState(pos.up(1)).isOf(Blocks.AIR))
                        {
                            world.setBlockState(pos.up(1), this.withAge(currentAge+1), 2);
                        }

                    }
                    else
                    {
                        world.setBlockState(pos, this.withAge(currentAge +1), 2);
                    }
                }

            }
        }
    }
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state)
    {
       int nextAge = this.getAge(state) + this.getGrowthAmount(world);
       int maxAge = this.getMaxAge();
       if (nextAge > MAX_AGE)
       {
           nextAge = maxAge;
       }
       if (this.getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR))
       {
           world.setBlockState(pos.up(1), this.withAge(nextAge), 2);

       }
       else
       {
           world.setBlockState(pos, this.withAge(nextAge -1), 2);
       }
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        return super.canPlaceAt(state, world, pos)
                || (world.getBlockState(pos.down(1)).isOf(this)
                && world.getBlockState(pos.down(1)).get(AGE) == 7);
    }

    @Override
    public int getMaxAge()
    {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_STAGE;
    }

    @Override
    protected IntProperty getAgeProperty()
    {
        return AGE;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

}
