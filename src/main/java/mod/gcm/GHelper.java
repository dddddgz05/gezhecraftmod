package mod.gcm;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class GHelper {
    public static Item getItem(String identifier){
        return Registries.ITEM.get(Identifier.tryParse(identifier));
    }
}
