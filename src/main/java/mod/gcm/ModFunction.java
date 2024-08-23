package mod.gcm;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ModFunction {
    public static boolean isRedstoneThing(Item item) {
        return item == Items.REDSTONE | item == Items.COMPARATOR | item == Items.REPEATER | item == Items.REDSTONE_TORCH;
    }
}
