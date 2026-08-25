package net.edrich.oncaffeine.block;

import net.edrich.oncaffeine.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Objects;

public class BeverageBlock extends Block {
    public BeverageBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){
        return Block.createCuboidShape(5.0,0.0,5.0,11.0,5.0,11.0);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos){

        return super.canPlaceAt(state, world, pos)
                && !(world.getBlockState(pos.down(1)).isIn(ModTags.Blocks.CUP_BLOCKS_UNPLACEABLE));


    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return Objects.requireNonNull(super.getPlacementState(context)).with(Properties.FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

}
