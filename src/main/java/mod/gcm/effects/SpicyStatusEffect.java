package mod.gcm.effects;

import mod.gcm.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;

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
        ParticleEffect particle = ParticleTypes.LAVA;
        entity.getWorld().addParticle(particle, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
        entity.damage(SPICY, amplifier + 1);
    }
}
