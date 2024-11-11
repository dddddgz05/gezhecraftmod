package mod.gcm.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class KeepInv {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((d, r, e) ->
            d.register(literal("gkeep").requires(s -> s.hasPermissionLevel(4))
                    .then(argument("newValue", BoolArgumentType.bool()).executes(KeepInv::set))
                    .executes(KeepInv::query))
        );
    }

    public static int set(CommandContext<ServerCommandSource> context) {
        World world = context.getSource().getWorld();
        GameRules.BooleanRule keepInventory = world.getGameRules().get(GameRules.KEEP_INVENTORY);
        boolean value = BoolArgumentType.getBool(context, "newValue");
        context.getSource().sendFeedback(() -> Text.translatable("message.command.gcm.keep_inventory.set", String.valueOf(value)), true);
        keepInventory.set(value, world.getServer());
        return 1;
    }

    public static int query(CommandContext<ServerCommandSource> context) {
        World world = context.getSource().getWorld();
        GameRules.BooleanRule keepInventory = world.getGameRules().get(GameRules.KEEP_INVENTORY);
        context.getSource().sendFeedback(() -> Text.translatable("message.command.gcm.keep_inventory.query", String.valueOf(keepInventory.get())), false);
        return 1;
    }
}
