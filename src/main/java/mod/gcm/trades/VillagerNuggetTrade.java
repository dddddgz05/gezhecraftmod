package mod.gcm.trades;

import mod.gcm.GItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class VillagerNuggetTrade {
    public static void add() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(GItems.VILLAGER_NUGGET, 6),
                    3, 10, 0.5f
            ));
        });
    }
}
