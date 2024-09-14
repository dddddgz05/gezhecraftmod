package mod.gcm.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Objects;

import static net.minecraft.server.command.CommandManager.literal;

public class KeepInventorySwitch {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(literal("gkeep").requires(source -> source.hasPermissionLevel(4))
                    .executes(KeepInventorySwitch::execute))
        );
    }

    public static int execute(CommandContext<ServerCommandSource> context) {
        World world = context.getSource().getWorld();
        GameRules.BooleanRule keepInventory = world.getGameRules().get(GameRules.KEEP_INVENTORY);
        Objects.requireNonNull(context.getSource().getPlayer()).sendMessage(Text.of(String.valueOf(!keepInventory.get())));
        keepInventory.set(!keepInventory.get(), world.getServer());
        return 1;
    }
}
