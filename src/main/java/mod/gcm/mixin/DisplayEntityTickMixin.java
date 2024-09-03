package mod.gcm.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.entity.decoration.DisplayEntity.*;

@Mixin(DisplayEntity.class)
public abstract class DisplayEntityTickMixin {
    @Shadow public abstract Box getVisibilityBoundingBox();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        Entity this_ = (Entity)(Object)this;
        if ((Entity)(Object)this instanceof BlockDisplayEntity blockDisplay) {
            World world = this_.getWorld();
            // bug
            if ((!world.getBlockState(this_.getBlockPos()).isAir())/* ||
                    world.getEntitiesByClass(BlockDisplayEntity.class, getVisibilityBoundingBox(), e -> true).size() > 1*/) {
                NbtCompound nbt = new NbtCompound();
                blockDisplay.writeNbt(nbt);
                Block block = Registries.BLOCK.get(Identifier.tryParse(((NbtCompound)nbt.get("block_state")).getString("Name")));
                blockDisplay.dropStack(block.asItem().getDefaultStack());
                this_.discard();
            }
        }
    }
}
