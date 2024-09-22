package mod.gcm.blocks;

import mod.gcm.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ExperienceBlock extends Block {
    public ExperienceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.experienceLevel > 0) {
            int level = player.experienceLevel;
            if (player.isSneaking()) {
                player.experienceLevel = 0;
                ItemEntity entity = new ItemEntity(world, player.getX(), player.getY(), player.getZ(),
                        new ItemStack(Main.EXPERIENCE, level));
                world.spawnEntity(entity);
            } else {
                player.experienceLevel -= 1;
                ItemEntity entity = new ItemEntity(world, player.getX(), player.getY(), player.getZ(),
                        new ItemStack(Main.EXPERIENCE));
                world.spawnEntity(entity);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
