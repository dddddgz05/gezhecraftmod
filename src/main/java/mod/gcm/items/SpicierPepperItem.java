package mod.gcm.items;

import mod.gcm.GStatusEffects;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class SpicierPepperItem extends Item {
    public SpicierPepperItem() {
        super(new FabricItemSettings().food(new FoodComponent.Builder()
                .statusEffect(new StatusEffectInstance(GStatusEffects.SPICY, 1200, 5), 1f).build()));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.addStatusEffect(new StatusEffectInstance(GStatusEffects.SPICY, 1200, 5));
        stack.decrement(1);
        return ActionResult.CONSUME;
    }
}
