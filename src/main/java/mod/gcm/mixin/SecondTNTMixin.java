package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public abstract class SecondTNTMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        ItemStack stack = context.getStack();
        if (EnchantmentHelper.getLevel(Main.TNT, stack) > 0) {
            if (!world.isClient) {
                BlockPos pos = context.getBlockPos();
                Vec3d pos1 = pos.toCenterPos();
                PlayerEntity player = context.getPlayer();
                TntEntity tnt = new TntEntity(world, pos1.x, pos1.y, pos1.z, player);
                NbtCompound nbt = new NbtCompound();
                tnt.writeNbt(nbt);
                nbt.put("block_state", NbtHelper.fromBlockState(world.getBlockState(pos)));
                tnt.readNbt(nbt);
                world.spawnEntity(tnt);
                world.removeBlock(pos, false);
                if (!player.getAbilities().creativeMode) {
                    stack.damage(1, world.random, (ServerPlayerEntity) player);
                }
            }
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
