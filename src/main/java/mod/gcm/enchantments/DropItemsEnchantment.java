package mod.gcm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class DropItemsEnchantment extends Enchantment {
    public DropItemsEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof PlayerEntity player && !target.isAlive()) {
            World world = user.getWorld();
            if (world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
                PlayerInventory inventory = player.getInventory();
                for (int i = 0; i < inventory.size(); ++i) {
                    ItemStack stack = inventory.getStack(i);
                    ItemEntity entity = new ItemEntity(world, target.getX(), target.getY(), target.getZ(), stack);
                    world.spawnEntity(entity);
                    inventory.setStack(i, ItemStack.EMPTY);
                }
            }
        }
    }
}
