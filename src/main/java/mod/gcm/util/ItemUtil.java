package mod.gcm.util;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public abstract class ItemUtil {
    // 判断物品是否具有一个属性，懒得用tag了，直接这样写吧
    public static boolean fire(Item item) {
        return item == Items.FLINT_AND_STEEL || item == Items.FIRE_CHARGE;
    }
}
