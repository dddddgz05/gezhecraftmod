package mod.gcm;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ID {
    public static Item item(String str){
        return Registries.ITEM.get(Identifier.tryParse(str));
    }
    public static Enchantment enchantment(String str){
        return Registries.ENCHANTMENT.get(Identifier.tryParse(str));
    }
}
