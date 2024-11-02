package mod.gcm.items;

import mod.gcm.GToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

public class VillagerSwordItem extends SwordItem {
    public VillagerSwordItem() {
        super(GToolMaterials.VILLAGER, 0, 0, new FabricItemSettings());
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof VillagerEntity villager) {
            TradeOfferList offers = villager.getOffers();
            TradeOfferList offers1 = new TradeOfferList();
            for (TradeOffer offer: offers) {
                ItemStack stack1 = offer.getOriginalFirstBuyItem();
                ItemStack stack2 = offer.getSellItem();
                offers1.add(new TradeOffer(stack2, stack1, offer.getMaxUses(), offer.getMerchantExperience(), 1f));
            }
            villager.setOffers(offers1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
