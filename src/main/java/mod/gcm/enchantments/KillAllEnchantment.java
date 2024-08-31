package mod.gcm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class KillAllEnchantment extends Enchantment {
    public KillAllEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        World world = user.getWorld();
        float damage = world.random.nextFloat() > 0.5f ? 0.5f : 0;
        ItemStack stack = user.getMainHandStack();
        if (stack.getItem() instanceof SwordItem sword) {
            damage = sword.getAttackDamage();
        } else if (stack.getItem() instanceof AxeItem axe) {
            damage = axe.getAttackDamage();
        }
        DamageSource source = user.getDamageSources().mobAttack(user);
        for (Entity entity: world.getEntitiesByClass(target.getClass(), Box.of(user.getPos(), 100, 30, 100), e -> e != target)) {
            entity.damage(source, damage);
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
