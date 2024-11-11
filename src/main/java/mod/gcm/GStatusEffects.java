package mod.gcm;

import mod.gcm.effects.LetOtherSeeYouStatusEffect;
import mod.gcm.effects.SpicyStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GStatusEffects {
    public static StatusEffect LET_OTHER_SEE_YOU = new LetOtherSeeYouStatusEffect(StatusEffectCategory.NEUTRAL, 0x3fad5b);
    public static Potion LET_OTHER_SEE_YOU_P = getPotion(LET_OTHER_SEE_YOU, 1200);
    public static StatusEffect SPICY = new SpicyStatusEffect(StatusEffectCategory.HARMFUL, 0xd02020);

    public static Potion getPotion(StatusEffect effect, int duration) {
        return getPotion(effect, duration, 1);
    }

    public static Potion getPotion(StatusEffect effect, int duration, int amplifier) {
        return new Potion(new StatusEffectInstance(effect, duration, amplifier));
    }

    public static void effect(StatusEffect effect, String path) {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of("gcm", path), effect);
    }

    public static void potion(Potion potion, String path) {
        Registry.register(Registries.POTION, Identifier.of("gcm", path), potion);
    }

    public static void effect() {
        effect(LET_OTHER_SEE_YOU, "let_other_see_you");
        potion(LET_OTHER_SEE_YOU_P, "let_other_see_you");
        effect(SPICY, "spicy");
    }
}
