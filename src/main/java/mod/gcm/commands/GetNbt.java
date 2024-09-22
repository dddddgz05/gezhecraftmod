package mod.gcm.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Set;
import java.util.function.Supplier;

import static net.minecraft.server.command.CommandManager.literal;

public class GetNbt {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(literal("gnbt").requires(source -> source.hasPermissionLevel(4))
                    .executes(GetNbt::execute))
        );
    }

    public static int execute(CommandContext<ServerCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            return 0;
        }
        ItemStack stack = player.getMainHandStack();
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) {
            context.getSource().sendFeedback(() ->
                    Text.translatable("message.command.gcm.nbt.query", stack.getName().getString(), getString(nbt, 0)), false);
            return 1;
        } else {
            context.getSource().sendFeedback(() ->
                    Text.translatable("message.command.gcm.nbt.empty", stack.getName().getString()), false);
            return 0;
        }
    }

    public static String getString(NbtCompound nbt, int level) {
        StringBuilder sb = new StringBuilder();
        String space = "  ".repeat(level);
        Set<String> keys = nbt.getKeys();
        sb.append("{\n");
        int i = 0;
        for (String key: keys) {
            NbtElement element = nbt.get(key);
            String string = element.asString();
            sb.append(space).append("  ").append(key).append(": ");
            if (string.charAt(0) == '{') {
                sb.append(getString((NbtCompound)element, level + 1));
            } else if (string.charAt(0) == '[') {
                sb.append(getString((NbtList)element, level + 1));
            } else {
                sb.append(string);
            }
            if (++i < keys.size()) {
                sb.append(",\n");
            }
        }
        sb.append("\n").append(space).append("  }");
        return sb.toString();
    }

    public static String getString(NbtList list, int level) {
        StringBuilder sb = new StringBuilder();
        String space = "  ".repeat(level);
        sb.append("[\n");
        int i = 0;
        for (NbtElement element: list) {
            sb.append(space);
            String string = element.asString();
            if (string.charAt(0) == '{') {
                sb.append(getString((NbtCompound)element, level + 1));
            } else if (string.charAt(0) == '[') {
                sb.append(getString((NbtList)element, level + 1));
            } else {
                sb.append(space).append("  ").append(element.asString());
            }
            if (++i < list.size()) {
                sb.append(",\n");
            }
        }
        sb.append("\n").append(space).append("]");
        return sb.toString();
    }
}
