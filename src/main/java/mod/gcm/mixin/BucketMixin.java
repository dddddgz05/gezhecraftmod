package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.entity.decoration.DisplayEntity.BlockDisplayEntity;

@Mixin(Item.class)
public abstract class BucketMixin {
    @Inject(method = "getName(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/text/Text;", at = @At("HEAD"), cancellable = true)
    public void getName(ItemStack stack, CallbackInfoReturnable<Text> cir) {
        if (stack.getItem() == Items.BUCKET && EnchantmentHelper.getLevel(Main.FAKE_BLOCK, stack) > 0) {
            if (stack.hasNbt()) {
                NbtCompound nbt = stack.getNbt();
                if (nbt.contains("block_state")) {
                    String identifierString = ((NbtCompound)nbt.get("block_state")).getString("Name");
                    cir.setReturnValue(Text.translatable("item.minecraft.bucket_with_block",
                            Registries.BLOCK.get(Identifier.tryParse(identifierString)).getName()));
                }
            }
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = context.getStack();
        if (stack.getItem() == Items.BUCKET && EnchantmentHelper.getLevel(Main.FAKE_BLOCK, stack) > 0) {
            NbtCompound nbt = stack.getOrCreateNbt();
            World world = context.getWorld();
            if (nbt.contains("block_state")) {
                Vec3i vec3i = context.getBlockPos().offset(context.getSide());
                BlockDisplayEntity blockDisplay = new BlockDisplayEntity(EntityType.BLOCK_DISPLAY, world);
                NbtCompound nbt1 = new NbtCompound();
                nbt1.put("block_state", nbt.get("block_state"));
                blockDisplay.readNbt(nbt1);
                blockDisplay.setPosition(Vec3d.of(vec3i));
                world.spawnEntity(blockDisplay);
                nbt.remove("block_state");
                stack.setNbt(nbt);
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                BlockPos pos = context.getBlockPos();
                BlockState state = world.getBlockState(pos);
                nbt.put("block_state", NbtHelper.fromBlockState(state));
                stack.setNbt(nbt);
                world.breakBlock(pos, false);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
