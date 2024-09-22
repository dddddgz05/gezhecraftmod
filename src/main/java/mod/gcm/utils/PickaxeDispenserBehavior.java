package mod.gcm.utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PickaxeDispenserBehavior implements DispenserBehavior {
    @Override
    public ItemStack dispense(BlockPointer pointer, ItemStack stack) {
        World world = pointer.world();
        BlockPos pos = pointer.pos();
        pos = pos.offset(world.getBlockState(pos).get(Properties.FACING));
        BlockState state = world.getBlockState(pos);
        if ((!state.isAir()) && ((MiningToolItem)stack.getItem()).getMaterial().getMiningLevel() >= MyBlockUtil.getMiningLevel(state)) {
            world.breakBlock(pos, true);
            if (stack.getMaxDamage() - stack.getDamage() > 1) {
                stack.damage(1, world.random, (ServerPlayerEntity) MyWorldUtil.getNearestPlayer(Vec3d.ofCenter(pos), world));
            } else {
                stack.decrement(1);
            }
        }
        return stack;
    }
}
