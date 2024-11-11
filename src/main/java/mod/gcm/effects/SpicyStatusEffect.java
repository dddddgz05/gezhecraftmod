package mod.gcm.effects;

import mod.gcm.Main;
import mod.gcm.utils.MyPosUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3d;

public class SpicyStatusEffect extends StatusEffect {
    public SpicyStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 15 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        DamageSource SPICY = new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(Main.SPICY_KEY));
        for (int i = 0; i < 20; ++i) {
            Vec3d pos = MyPosUtil.offset(entity.getEyePos(), 0.5);
            entity.getWorld().addParticle(ParticleTypes.LAVA, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
        }
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 75));
        entity.damage(SPICY, amplifier + 1);
    }
}
