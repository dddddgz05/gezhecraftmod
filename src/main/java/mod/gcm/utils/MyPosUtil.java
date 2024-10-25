package mod.gcm.utils;

import net.minecraft.util.math.Vec3d;

import java.util.Random;

public abstract class MyPosUtil {
    public static Vec3d offset(Vec3d pos, double offsetBound) {
        Random random = new Random();
        double offsetX = random.nextDouble(-offsetBound, offsetBound);
        double offsetY = random.nextDouble(-offsetBound, offsetBound);
        double offsetZ = random.nextDouble(-offsetBound, offsetBound);
        return new Vec3d(pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ);
    }
}
