package mod.gcm;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GParticles {
    public static final DefaultParticleType DOUBLE_ELEVEN = FabricParticleTypes.simple();

    public static void particle() {
        particle(DOUBLE_ELEVEN, "double_eleven");
    }

    public static void particle(DefaultParticleType type, String path) {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of("gcm", path), type);
    }
}
