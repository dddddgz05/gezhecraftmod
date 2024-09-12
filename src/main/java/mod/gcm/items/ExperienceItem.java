package mod.gcm.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExperienceItem extends Item {
    public ExperienceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            int count = stack.getCount();
            stack.decrement(count);
            user.addExperienceLevels(count);
        } else {
            stack.decrement(1);
            user.addExperienceLevels(1);
        }
        return TypedActionResult.consume(stack);
    }
}
