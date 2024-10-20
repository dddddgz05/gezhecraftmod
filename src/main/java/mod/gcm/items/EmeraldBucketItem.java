package mod.gcm.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class EmeraldBucketItem extends Item {
    public EmeraldBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        if (stack.hasNbt()) {
            NbtCompound nbt = stack.getNbt();
            if (nbt.contains("block_state")) {
                String identifierString = ((NbtCompound)nbt.get("block_state")).getString("Name");
                return Text.translatable("item.minecraft.bucket_with_block",
                        Registries.BLOCK.get(Identifier.tryParse(identifierString)).getName());
            }
        }
        return super.getName(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        NbtCompound nbt = stack.getOrCreateNbt();
        World world = context.getWorld();
        if (nbt.contains("block_state")) {
            Vec3i vec3i = context.getBlockPos().offset(context.getSide());
            DisplayEntity.BlockDisplayEntity blockDisplay = new DisplayEntity.BlockDisplayEntity(EntityType.BLOCK_DISPLAY, world);
            NbtCompound nbt1 = new NbtCompound();
            nbt1.put("block_state", nbt.get("block_state"));
            blockDisplay.readNbt(nbt1);
            blockDisplay.setPosition(Vec3d.of(vec3i));
            world.spawnEntity(blockDisplay);
            nbt.remove("block_state");
            stack.setNbt(nbt);
            return ActionResult.SUCCESS;
        } else {
            BlockPos pos = context.getBlockPos();
            BlockState state = world.getBlockState(pos);
            nbt.put("block_state", NbtHelper.fromBlockState(state));
            stack.setNbt(nbt);
            world.breakBlock(pos, false);
            return ActionResult.SUCCESS;
        }
    }
}
