package mod.gcm.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarItem extends Item {
    public CalendarItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            user.sendMessage(Text.translatable("message.item.gcm.calendar.open").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
            user.sendMessage(Text.translatable("message.item.gcm.calendar.date", dateString).formatted(Formatting.BOLD));
            user.sendMessage(Text.translatable("message.item.gcm.calendar.close").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }
}
