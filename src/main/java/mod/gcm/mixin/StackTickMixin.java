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
//            我他妈想知道为什么 inventoryTick 会被调用不止一次啊？？？？？
//            NbtCompound nbt = stack.getNbt();
//            assert nbt != null;
//            if (nbt.contains("lastTime")) {
//                long td = nbt.getLong("lastTime") - world.getTime();
//                if ((td >= 0 && td < 5) || (td <= 0 && td > -5)) {
//                    return;
//                }
//            } else {
//                nbt.putLong("lastTime", world.getTime());
//            }
//            PlayerInventory inventory = player.getInventory();
//            List<Integer> emptyStacks = new ArrayList<>();
//            for (int i = 0; i < 9; ++i) {
//                if (i != slot) {
//                    if (inventory.getStack(i).isEmpty()) {
//                        emptyStacks.add(i);
//                    }
//                }
//            }
//            PlayerInventory inventory = player.getInventory();
//            List<Integer> emptyStacks = new ArrayList<>();
//            for (int i = 0; i < 9; ++i) {
//                if (i != slot) {
//                    if (inventory.getStack(i).isEmpty()) {
//                        emptyStacks.add(i);
//                    }
//                }
//            }
//            if (!emptyStacks.isEmpty()) {
//                int targetSlot;
//                do {
//                    targetSlot = emptyStacks.get(world.random.nextBetweenExclusive(0, emptyStacks.size() - 1));
//                }
//                while (targetSlot == slot);
//                inventory.removeStack(slot);
//                inventory.setStack(targetSlot, stack);
//            }
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
