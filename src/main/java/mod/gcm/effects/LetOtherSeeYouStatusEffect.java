package mod.gcm.effects;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class LetOtherSeeYouStatusEffect extends StatusEffect {
    public LetOtherSeeYouStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        amplifier += 1;
        World world = entity.getWorld();
        Box box = entity.getBoundingBox().expand(amplifier * 10);
        List<Entity> entities = world.getOtherEntities(entity, box, entity1 -> entity1 instanceof LivingEntity);
        for (Entity entity1 : entities) {
            entity1.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, entity.getEyePos());
//            if (entity1 instanceof MobEntity mob) {
//                entity.sendMessage(Text.of(String.valueOf(mob.isInAttackRange(entity))));
//                if (mob.isInAttackRange(entity)) {
//                    mob.onAttacking(entity);
//                }
//            }
        }
    }
}
