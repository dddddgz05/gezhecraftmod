package mod.gcm.mixin;

import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class TNTAndFireChargeMixin {
    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean creativeMode = user.getAbilities().creativeMode;
        ItemStack mainHandStack = user.getMainHandStack();
        Item mainHandItem = mainHandStack.getItem();
        ItemStack offHandStack = user.getOffHandStack();
        Item offHandItem = offHandStack.getItem();
        if (mainHandItem == Items.TNT) {
            boolean canCreateTnt = false;
            if (offHandItem == Items.FLINT_AND_STEEL) {
                canCreateTnt = true;
                if (!creativeMode) {
                    offHandStack.damage(1, (Random) null, null);
                }
            } else if (offHandItem == Items.FIRE_CHARGE) {
                canCreateTnt = true;
                if (!creativeMode) {
                    offHandStack.decrement(1);
                }
            }
            if (canCreateTnt) {
                TntEntity tnt = new TntEntity(world, user.getX(), user.getEyeY(), user.getZ(), user);
                tnt.setVelocity(Vec3d.fromPolar(user.getPitch(), user.getYaw()));
                world.spawnEntity(tnt);
                if (!creativeMode) {
                    mainHandStack.decrement(1);
                }
            }
        } else if (mainHandItem == Items.FIRE_CHARGE) {
            Vec3d v = Vec3d.fromPolar(user.getPitch(), user.getYaw());
            boolean decrementOffHandStack = false;
            if (offHandItem == Items.FIRE_CHARGE) {
                FireballEntity fireball = new FireballEntity(world, user, v.x, v.y, v.z, world.random.nextBetween(1, 2));
                fireball.setPosition(user.getX(), user.getEyeY(), user.getZ());
                world.spawnEntity(fireball);
            } else {
                SmallFireballEntity smallFireball = new SmallFireballEntity(world, user, v.x, v.y, v.z);
                smallFireball.setPosition(user.getX(), user.getEyeY(), user.getZ());
                world.spawnEntity(smallFireball);
                decrementOffHandStack = true;
            }
            if (!creativeMode) {
                mainHandStack.decrement(1);
                if (decrementOffHandStack) {
                    offHandStack.decrement(1);
                }
            }
        }
    }
}
