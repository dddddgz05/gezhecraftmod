package mod.gcm.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ChatBlock extends FacingBlock {
    public ChatBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return createCodec(ChatBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            Direction direction = state.get(FACING);
            BlockPos newPos = pos.offset(direction);
            BlockState signState = world.getBlockState(newPos);
            if (signState.isIn(BlockTags.ALL_SIGNS)) {
                // 这是一条注释
                SignBlockEntity sbEntity = (SignBlockEntity)world.getBlockEntity(newPos);
                SignText signText = sbEntity.getFrontText();
                String colorName = signText.getColor().getName();
                Text[] messages = signText.getMessages(false);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; ++i) {
                    String string = messages[i].getString();
                    if (i > 0 && !string.isEmpty()) {
                        sb.append("\n");
                    }
                    sb.append(string);
                }
                PlayerEntity p1 = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 100, false);
                if (p1 != null) {
                    String playerName = p1.getName().getString();
                    sb.insert(0, ": ").insert(0, playerName);
                }
                Text text = Text.literal(sb.toString()).formatted(Formatting.byName(colorName));
                System.out.println(text);
                for (PlayerEntity player: world.getPlayers()) {
                    player.sendMessage(text);
                }
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerLookDirection());
    }
}
