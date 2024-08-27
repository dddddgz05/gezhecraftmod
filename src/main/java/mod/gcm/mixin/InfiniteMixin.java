package mod.gcm.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class InfiniteMixin {
    @Inject(method = "decrement", at = @At("HEAD"), cancellable = true)
    public void decrement(int amount, CallbackInfo ci) {
        if (EnchantmentHelper.getLevel(Enchantments.INFINITY, (ItemStack)(Object)this) > 0) {
            ci.cancel();
        }
    }
}
