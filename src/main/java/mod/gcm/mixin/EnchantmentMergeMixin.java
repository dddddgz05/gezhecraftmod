package mod.gcm.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(Entity.class)
public abstract class EnchantmentMergeMixin {
    @Shadow public abstract World getWorld();

    @Shadow public abstract Box getBoundingBox();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (((Entity)(Object)this) instanceof ItemEntity item) {
            ItemStack stack = item.getStack();
            if (stack.getItem() == Items.ENCHANTED_BOOK) return;
            for (ItemEntity item1: getWorld().getEntitiesByClass(ItemEntity.class, getBoundingBox().expand(0.5, 0.5, 0.5),
                        other -> other.getStack().getItem() == Items.ENCHANTED_BOOK)) {
                ItemStack stack1 = item1.getStack();
                NbtList enchantments = EnchantedBookItem.getEnchantmentNbt(stack1);
                for (int i = 0; i < enchantments.size(); ++i) {
                    NbtCompound nbt = enchantments.getCompound(i);
                    Enchantment enchantment = Registries.ENCHANTMENT.get(Identifier.tryParse(nbt.getString("id")));
                    if (enchantment != null)
                        if (getWorld().random.nextFloat() > 0.1f) {
                            Map<Enchantment, Integer> map = new HashMap<>();
                            map.put(enchantment, (int) nbt.getShort("lvl"));
                            map.putAll(EnchantmentHelper.get(stack));
                            EnchantmentHelper.set((map), stack);
                            item1.discard();
                        } else {
                            PlayerEntity n = null;
                            float distance = Float.POSITIVE_INFINITY;
                            for (PlayerEntity player: getWorld().getPlayers()) {
                                if (player.distanceTo(item1) < distance) {
                                    distance = player.distanceTo(item1);
                                    n = player;
                                }
                            }
                            if (n != null) {
                                getWorld().createExplosion(n, n.getX(), n.getY(), n.getZ(), 4.0F, World.ExplosionSourceType.MOB);
                            }
                    }
                }
            }
        }
    }
}
