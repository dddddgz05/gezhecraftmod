package mod.gcm.mixin;

import mod.gcm.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "onSteppedOn", at = @At("HEAD"))
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (entity instanceof LivingEntity l && state.getBlock() == Blocks.HOPPER && l.getBlockPos().equals(pos) && l.getWidth() <= 0.625) {
            DamageSource HOPPER = new DamageSource(
                    l.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(Main.HOPPER_KEY));
            l.damage(HOPPER, l.getHealth());
        }
    }
}
