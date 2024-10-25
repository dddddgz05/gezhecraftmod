package mod.gcm.mixin;

import mod.gcm.GItems;
import mod.gcm.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem() == Items.IRON_INGOT) {
            if (user.experienceLevel > 0) {
                int level = user.experienceLevel;
                if (user.isSneaking()) {
                    user.experienceLevel = 0;
                    ItemEntity entity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(),
                            new ItemStack(GItems.EXPERIENCE, level));
                    world.spawnEntity(entity);
                } else {
                    user.experienceLevel -= 1;
                    ItemEntity entity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(),
                            new ItemStack(GItems.EXPERIENCE));
                    world.spawnEntity(entity);
                }
            }
        }
    }
    
    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    public void getMaxUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        int level = EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, stack);
        if (stack.isFood() && level > 0) {
            float origin = stack.getFoodComponent().isSnack() ? 16 : 32;
            origin /= level + 1;
            cir.setReturnValue(Math.round(origin));
        }
    }
}
