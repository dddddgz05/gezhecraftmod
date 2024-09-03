package mod.gcm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AttackAllEnchantment extends Enchantment {
    public AttackAllEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        World world = user.getWorld();
        ItemStack stack = user.getMainHandStack();
        for (Entity entity: world.getEntitiesByClass(target.getClass(), Box.of(user.getPos(), 100, 30, 100), e -> e != target)) {
            user.tryAttack(entity);
            if (user instanceof ServerPlayerEntity server) {
                if (!server.getAbilities().creativeMode) {
                    stack.damage(1, world.random, server);
                }
            } else {
                stack.damage(1, world.random, null);
            }
        }
    }
}
