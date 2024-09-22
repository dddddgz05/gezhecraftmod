package mod.gcm.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class MyWorldUtil {
    public static PlayerEntity getNearestPlayer(Vec3d pos, World world) {
        double distance = Double.POSITIVE_INFINITY;
        PlayerEntity player = null;
        for (PlayerEntity each: world.getPlayers()) {
            double temp = MyMathUtil.distanceTo(pos, each.getPos());
            if (temp < distance) {
                distance = temp;
                player = each;
            }
        }
        return player;
    }
}
