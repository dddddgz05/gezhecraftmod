package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PotionItem.class)
public abstract class WaterBottleMixin {
    @Inject(method = "finishUsing", at = @At("HEAD"))
    public void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        Map<StatusEffect, StatusEffectInstance> effects = user.getActiveStatusEffects();
        if (effects.containsKey(Main.SPICY_EFFECT)) {
            StatusEffectInstance old = effects.get(Main.SPICY_EFFECT);
            int oldAmplifier = old.getAmplifier();
            user.removeStatusEffect(Main.SPICY_EFFECT);
            if (oldAmplifier > 0) {
                StatusEffectInstance newInstance = new StatusEffectInstance(Main.SPICY_EFFECT, old.getDuration(), old.getAmplifier() - 1);
                user.addStatusEffect(newInstance);
            }
        }
    }
}
