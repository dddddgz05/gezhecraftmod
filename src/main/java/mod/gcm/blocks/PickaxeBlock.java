package mod.gcm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
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
                Block block1 = world.getBlockState(target).getBlock();
                if (block1.getHardness() == -1) {
                    for (PlayerEntity player: world.getPlayers()) {
                        player.sendMessage(Text.translatable("message.block.gcm.pickaxe_block.cannot_mine",
                                target.getX(), target.getY(), target.getZ(), block1.getName().getString()));
                    }
                } else {
                    world.breakBlock(target, true);
                }
            }
        }
    }
}
