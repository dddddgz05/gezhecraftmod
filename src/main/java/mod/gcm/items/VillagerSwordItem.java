package mod.gcm.items;

import mod.gcm.GEnchantments;
import mod.gcm.GParticles;
import mod.gcm.GToolMaterials;
import mod.gcm.utils.MyPosUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

public class VillagerSwordItem extends SwordItem {
    public VillagerSwordItem() {
        super(GToolMaterials.VILLAGER, 3, -2.4F, new FabricItemSettings());
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof VillagerEntity villager) {
            // 村民原来的交易列表
            TradeOfferList origin = villager.getOffers();
            // 更改后的交易列表
            TradeOfferList newList = new TradeOfferList();
            if (EnchantmentHelper.getLevel(GEnchantments.DOUBLE_ELEVEN, stack) > 0) {
                for (TradeOffer offer: origin) {
                    ItemStack buy = new ItemStack(Items.EMERALD, 64);
                    ItemStack sell = Items.EMERALD.getDefaultStack();
                    newList.add(new TradeOffer(buy, buy, sell, offer.getMaxUses(), offer.getMerchantExperience(), 1f));
                }
                for (int i = 0; i < 20; ++i) {
                    Vec3d pos = MyPosUtil.offset(entity.getEyePos(), 0.5);
                    entity.getWorld().addParticle(GParticles.DOUBLE_ELEVEN,
                            pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
                }
            } else {
                for (TradeOffer offer: origin) {
                    // 只会卖原来买的第一个物品
                    ItemStack buy = offer.getOriginalFirstBuyItem();
                    ItemStack sell = offer.getSellItem();
                    // 其他不变
                    int maxUses = offer.getMaxUses();
                    int experience = offer.getMerchantExperience();
                    newList.add(new TradeOffer(sell, buy, maxUses, experience, 1f));
                }
            }
            villager.setOffers(newList);
            // 损失2耐久度
            stack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
