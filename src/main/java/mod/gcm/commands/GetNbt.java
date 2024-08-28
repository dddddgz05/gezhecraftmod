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

public class GetNbt {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(literal("nbt").requires(source -> source.hasPermissionLevel(4))
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
            context.getSource().sendFeedback(() -> Text.of(nbt.toString()), true);
        }
        return 1;
    }
}
