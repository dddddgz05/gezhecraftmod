package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ExplosiveMixin {
    @Inject(method = "onPlayerCollision", at = @At("HEAD"))
    public void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        ItemEntity this_ = (ItemEntity)(Object)this;
        if (EnchantmentHelper.getLevel(Main.EXPLOSIVE, this_.getStack()) > 0) {
            this_.getWorld().spawnEntity(new TntEntity(this_.getWorld(), this_.getX(), this_.getY(), this_.getZ(), null));
        }
    }
}
