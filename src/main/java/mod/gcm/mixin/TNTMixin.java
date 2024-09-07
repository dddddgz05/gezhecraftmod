package mod.gcm.mixin;

import mod.gcm.Main;
import mod.gcm.util.ItemUtil;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class TNTMixin {
    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean creativeMode = user.getAbilities().creativeMode;
        ItemStack mainHandStack = user.getMainHandStack();
        Item mainHandItem = mainHandStack.getItem();
        ItemStack offHandStack = user.getOffHandStack();
        Item offHandItem = offHandStack.getItem();
        if (mainHandItem instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (ItemUtil.fire(offHandItem)) {
                if (mainHandItem == Items.TNT || EnchantmentHelper.getLevel(Main.TNT, mainHandStack) > 0) {
                    TntEntity tnt = new TntEntity(world, user.getX(), user.getEyeY(), user.getZ(), user);
                    tnt.setVelocity(Vec3d.fromPolar(user.getPitch(), user.getYaw()));
                    NbtCompound nbt = new NbtCompound();
                    tnt.writeNbt(nbt);
                    NbtCompound nbt1 = new NbtCompound();
                    nbt1.putString("Name", Registries.BLOCK.getId(block).toString());
                    nbt.put("block_state", nbt1);
                    tnt.readNbt(nbt);
                    world.spawnEntity(tnt);
                }
            }
            if (!creativeMode) {
                if (offHandStack.isDamageable()) {
                    offHandStack.damage(1, world.random, (ServerPlayerEntity)user);
                } else {
                    offHandStack.decrement(1);
                }
                mainHandStack.decrement(1);
            }
        } else if (mainHandItem == Items.FIRE_CHARGE) {
            Vec3d v = Vec3d.fromPolar(user.getPitch(), user.getYaw());
            if (offHandItem == Items.FIRE_CHARGE) {
                FireballEntity fireball = new FireballEntity(world, user, v.x, v.y, v.z, world.random.nextBetween(1, 2));
                fireball.setPosition(user.getX(), user.getEyeY(), user.getZ());
                world.spawnEntity(fireball);
            } else {
                SmallFireballEntity smallFireball = new SmallFireballEntity(world, user, v.x, v.y, v.z);
                smallFireball.setPosition(user.getX(), user.getEyeY(), user.getZ());
                world.spawnEntity(smallFireball);
            }
            if (!creativeMode) {
                if (offHandStack.isDamageable()) {
                    offHandStack.damage(1, world.random, (ServerPlayerEntity)user);
                } else {
                    offHandStack.decrement(1);
                }
                mainHandStack.decrement(1);
            }
        }
    }
}