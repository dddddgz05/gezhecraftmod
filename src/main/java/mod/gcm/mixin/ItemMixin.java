package mod.gcm.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
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
