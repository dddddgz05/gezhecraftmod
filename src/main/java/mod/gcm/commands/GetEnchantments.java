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

import java.util.function.Supplier;

import static net.minecraft.server.command.CommandManager.literal;

public class GetEnchantments {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(literal("gench")
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
        if (enchantments.isEmpty()) {
            context.getSource().sendFeedback(() ->
                    Text.translatable("message.command.gcm.enchantments.empty", stack.getName().getString()), false);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < enchantments.size(); ++i) {
                NbtCompound nbt = enchantments.getCompound(i);
                Identifier enchantment = Identifier.tryParse(nbt.getString("id"));
                if (enchantment != null) {
                    String string = "enchantment." + enchantment.getNamespace() + "." + enchantment.getPath();
                    sb.append(i + 1).append(". ").append(Text.translatable(string).getString()).append(" ");
                    sb.append(Text.translatable("enchantment.level." + nbt.getShort("lvl")).getString());
                }
            }
            context.getSource().sendFeedback(() ->
                    Text.translatable("message.command.gcm.enchantments.query", sb.toString(), stack.getName().getString()), false);
        }
        return 1;
    }
}
