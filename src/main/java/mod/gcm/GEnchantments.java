package mod.gcm;

import mod.gcm.enchantments.AttackAllEnchantment;
import mod.gcm.enchantments.NoKeepInventoryEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.enchantment.Enchantment.Rarity.UNCOMMON;
import static net.minecraft.enchantment.EnchantmentTarget.BREAKABLE;
import static net.minecraft.entity.EquipmentSlot.MAINHAND;

public class GEnchantments {
    public static Enchantment ATTACK_ALL = new AttackAllEnchantment(UNCOMMON, BREAKABLE, new EquipmentSlot[]{MAINHAND});
    public static Enchantment NO_KEEP_INVENTORY = new NoKeepInventoryEnchantment(UNCOMMON, BREAKABLE, new EquipmentSlot[]{MAINHAND});

    public static void enchantment(Enchantment enchantment, String path) {
        Registry.register(Registries.ENCHANTMENT, Identifier.of("gcm", path), enchantment);
    }

    public static void register() {
        enchantment(ATTACK_ALL, "attack_all");
        enchantment(NO_KEEP_INVENTORY, "no_keep_inventory");
    }
}
