package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class StackTickMixin {
    @Shadow public abstract Text getName();

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void inventoryTick(World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        ItemStack stack = (ItemStack)(Object)this;
        if (selected && entity instanceof PlayerEntity player && EnchantmentHelper.getLevel(Main.CANNOT_SELECT, stack) > 0) {
            Inventory inventory = player.getInventory();
            List<Integer> emptyStacks = new ArrayList<>();
            for (int i = 0; i < 9; ++i) {
                if (i != slot) {
                    if (inventory.getStack(i).isEmpty()) {
                        emptyStacks.add(i);
                    }
                }
            }
            if (emptyStacks.isEmpty()) {
                // 没有可以传送的空间了
                player.sendMessage(Text.translatable("message.enchantment.gcm.cannot_select.no_place",
                        getName().getString()));
            } else {
                // 还可以传送
                int targetSlot = emptyStacks.get(world.random.nextBetweenExclusive(0, emptyStacks.size() - 1));
                inventory.setStack(slot, ItemStack.EMPTY);
                inventory.setStack(targetSlot, stack);
            }
        }
    }
}
