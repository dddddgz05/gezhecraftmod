package mod.gcm.mixin;

import mod.gcm.GStatusEffects;
import mod.gcm.Main;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingMixin {
    @Inject(method = "registerDefaults", at = @At("HEAD"))
    private static void registerDefaults(CallbackInfo ci) {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, Items.ENDER_EYE, GStatusEffects.LET_OTHER_SEE_YOU_P);
    }
}
