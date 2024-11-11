package mod.gcm;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Set;

import static net.minecraft.server.command.CommandManager.literal;

public class GCommands {
    public static void command() {
        gench.register();
        gnbt.register();
    }

    public static class gench {
        public static void register() {
            CommandRegistrationCallback.EVENT.register((dispatcher, r, e) ->
                    dispatcher.register(literal("gench").executes(gench::execute))
            );
        }

        public static int execute(CommandContext<ServerCommandSource> context) {
            PlayerEntity player = context.getSource().getPlayer();
            if (player == null) {
                return 0;
            }
            ItemStack stack = player.getMainHandStack();
            String name = stack.getName().getString();
            NbtList enchantments = stack.getEnchantments();
            if (enchantments.isEmpty()) {
                context.getSource().sendFeedback(() ->
                        Text.translatable("message.command.gcm.enchantments.empty", name), false);
            } else {
                StringBuilder sb = new StringBuilder().append("\n");
                for (int i = 0; i < enchantments.size(); ++i) {
                    NbtCompound nbt = (NbtCompound)enchantments.get(i);
                    short level = nbt.getShort("lvl");
                    String enchantment = ID.enchantment(nbt.getString("id")).getName(level).getString();
                    sb.append(i + 1).append(". ").append(enchantment).append("\n");
                }
                context.getSource().sendFeedback(() ->
                        Text.translatable("message.command.gcm.enchantments.query", name, sb.toString()), false);
            }
            return 1;
        }
    }

    public static class gnbt {
        public static void register() {
            CommandRegistrationCallback.EVENT.register((d, r, e) ->
                    d.register(literal("gnbt").requires(s -> s.hasPermissionLevel(4)).executes(gnbt::execute))
            );
        }

        public static int execute(CommandContext<ServerCommandSource> context) {
            PlayerEntity player = context.getSource().getPlayer();
            if (player == null) {
                return 0;
            }
            ItemStack stack = player.getMainHandStack();
            String name = stack.getName().getString();
            NbtCompound nbt = stack.getNbt();
            if (nbt != null) {
                context.getSource().sendFeedback(() ->
                        Text.translatable("message.command.gcm.nbt.query", name, getString(nbt, 1)), false);
                return 1;
            } else {
                context.getSource().sendFeedback(() ->
                        Text.translatable("message.command.gcm.nbt.empty", name), false);
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
                sb.append(space).append(key).append(": ");
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
            sb.append("\n").append(space).append("}");
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

}
