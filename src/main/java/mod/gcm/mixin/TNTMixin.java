package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class TNTMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem() instanceof BlockItem item && EnchantmentHelper.getLevel(Main.TNT, stack) > 0) {
            TntEntity tnt = new TntEntity(world, user.getX(), user.getEyeY(), user.getZ(), user);
            tnt.setVelocity(user.getVelocity().add(Vec3d.fromPolar(user.getPitch(), user.getYaw())));
            NbtCompound nbt = new NbtCompound();
            tnt.writeNbt(nbt);
            nbt.put("block_state", NbtHelper.fromBlockState(item.getBlock().getDefaultState()));
            tnt.readNbt(nbt);
            world.spawnEntity(tnt);
            stack.decrement(1);
            cir.setReturnValue(TypedActionResult.success(stack));
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return;
        }
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(context.getBlockPos());
        ItemStack stack = context.getStack();
        if (stack.getItem() == Items.FLINT_AND_STEEL && EnchantmentHelper.getLevel(Main.TNT, stack) > 0) {
            TntEntity tnt = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), player);
            tnt.setVelocity(player.getVelocity().add(Vec3d.fromPolar(player.getPitch(), player.getYaw())));
            NbtCompound nbt = new NbtCompound();
            tnt.writeNbt(nbt);
            nbt.put("block_state", NbtHelper.fromBlockState(state));
            tnt.readNbt(nbt);
            world.spawnEntity(tnt);
            stack.decrement(1);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
