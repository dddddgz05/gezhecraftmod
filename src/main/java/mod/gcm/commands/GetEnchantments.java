package mod.gcm.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.server.command.CommandManager.literal;

public class GetEnchantments {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(literal("ench")
                    .executes(GetEnchantments::execute))
        );
    }

    public static int execute(CommandContext<ServerCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            return 0;
        }
        ItemStack stack = player.getMainHandStack();
        NbtList enchantments = stack.getEnchantments();
        for (int i = 0; i < enchantments.size(); ++i) {
            NbtCompound nbt = enchantments.getCompound(i);
            Identifier enchantment = Identifier.tryParse(nbt.getString("id"));
            if (enchantment != null) {
                String string = "enchantment." + enchantment.getNamespace() + "." + enchantment.getPath();
                int level = nbt.getShort("lvl");
                if (level < 10) {
                    player.sendMessage(Text.of(i + 1 + "." + Text.translatable(string).getString() + " " +
                            Text.translatable("enchantment.level." + level)));
                } else {
                    player.sendMessage(Text.of(i + 1 + "." + Text.translatable(string).getString() + " " +
                            level));
                }
            }
        }
        return 1;
    }
}
