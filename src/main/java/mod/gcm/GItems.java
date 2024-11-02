package mod.gcm;

import mod.gcm.items.CalendarItem;
import mod.gcm.items.EmeraldBucketItem;
import mod.gcm.items.ExperienceItem;
import mod.gcm.items.VillagerSwordItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GItems {
    public static Item CALENDAR = new CalendarItem(new FabricItemSettings());
    public static Item EMERALD_BUCKET = new EmeraldBucketItem(new FabricItemSettings().maxCount(1));
    public static Item EXPERIENCE = new ExperienceItem(new FabricItemSettings());
    public static Item SPICIER_PEPPER = new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .statusEffect(new StatusEffectInstance(GStatusEffects.SPICY, 5), 1f).build()));
    public static Item VILLAGER_INGOT = new Item(new FabricItemSettings());
    public static Item VILLAGER_NUGGET = new Item(new FabricItemSettings());
    public static Item VILLAGER_SWORD = new VillagerSwordItem();

    public static void item(Item item, String path) {
        Registry.register(Registries.ITEM, Identifier.of("gcm", path), item);
    }

    public static void register() {
        item(CALENDAR, "calendar");
        item(EMERALD_BUCKET, "emerald_bucket");
        item(EXPERIENCE, "experience");
        item(SPICIER_PEPPER, "spicier_pepper");
        item(VILLAGER_INGOT, "villager_ingot");
        item(VILLAGER_NUGGET, "villager_nugget");
        item(VILLAGER_SWORD, "villager_sword");
    }
}
