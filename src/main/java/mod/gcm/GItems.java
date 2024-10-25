package mod.gcm;

import mod.gcm.items.CalendarItem;
import mod.gcm.items.EmeraldBucketItem;
import mod.gcm.items.ExperienceItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GItems {
    public static Item CALENDAR = new CalendarItem(new FabricItemSettings());
    public static Item EMERALD_BUCKET = new EmeraldBucketItem(new FabricItemSettings());
    public static Item EXPERIENCE = new ExperienceItem(new FabricItemSettings());

    public static void item(Item item, String path) {
        Registry.register(Registries.ITEM, Identifier.of("gcm", path), item);
    }

    public static void register() {
        item(CALENDAR, "calendar");
        item(EMERALD_BUCKET, "emerald_bucket");
        item(EXPERIENCE, "experience");
    }
}
