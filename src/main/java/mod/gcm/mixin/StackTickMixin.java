package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class StackTickMixin {
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void inventoryTick(World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        ItemStack stack = (ItemStack)(Object)this;
        if (selected && entity instanceof PlayerEntity player && EnchantmentHelper.getLevel(Main.CANNOT_SELECT, stack) > 0) {
            PlayerInventory inventory = player.getInventory();
            int targetSlot = slot;
            for (int i = 0; i < 9; ++i) {
                if (i != slot && (inventory.getStack(i).isEmpty() || inventory.getStack(i) == stack)) {
                    targetSlot = i;
                    break;
                }
            }
            inventory.removeStack(slot);
            inventory.setStack(targetSlot, stack);
        }
    }
}
