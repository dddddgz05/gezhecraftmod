package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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
}
