package mod.gcm;

import mod.gcm.enchantments.AttackAllEnchantment;
import mod.gcm.enchantments.DoubleElevenEnchantment;
import mod.gcm.enchantments.DropItemsEnchantment;
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
    public static Enchantment DROP_ITEMS = new DropItemsEnchantment(UNCOMMON, BREAKABLE, new EquipmentSlot[]{MAINHAND});
    public static Enchantment DOUBLE_ELEVEN =
            new DoubleElevenEnchantment(UNCOMMON, BREAKABLE, new EquipmentSlot[]{MAINHAND});

    public static void enchantment(Enchantment enchantment, String path) {
        Registry.register(Registries.ENCHANTMENT, Identifier.of("gcm", path), enchantment);
    }

    public static void enchantment() {
        enchantment(ATTACK_ALL, "attack_all");
        enchantment(DROP_ITEMS, "drop_items");
        enchantment(DOUBLE_ELEVEN, "double_eleven");
    }
}
