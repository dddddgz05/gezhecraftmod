package mod.gcm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PickaxeBlock extends Block {
    public PickaxeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        for (Direction direction: DIRECTIONS) {
            Block block = world.getBlockState(pos.offset(direction)).getBlock();
            if (block == Blocks.GLASS) {
                BlockPos target = pos.offset(direction.getOpposite());
                world.breakBlock(target, true);
            }
        }
    }
}
