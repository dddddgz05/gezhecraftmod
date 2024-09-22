package mod.gcm.utils;

import net.minecraft.util.math.Vec3d;

public abstract class MyMathUtil {
    public static double distanceTo(Vec3d vec1, Vec3d vec2) {
        double dx = vec1.getX() - vec2.getX();
        double dy = vec1.getY() - vec2.getY();
        double dz = vec1.getZ() - vec2.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
