package mod.gcm.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;

import java.util.Optional;

import static net.minecraft.server.command.CommandManager.literal;

public class TpDeath {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((d, r, e) ->
            d.register(literal("gtpd").requires(s -> s.hasPermissionLevel(4)).executes(TpDeath::execute))
        );
    }

    public static int execute(CommandContext<ServerCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            return 0;
        }
        Optional<GlobalPos> lastDeathPos = player.getLastDeathPos();
        if (lastDeathPos.isPresent()) {
            BlockPos pos = lastDeathPos.get().getPos();
            player.teleport(pos.getX(), pos.getY(), pos.getZ());
            context.getSource().sendFeedback(() -> Text.translatable("message.command.gcm.tp_death"), false);
        }
        return 1;
    }
}
