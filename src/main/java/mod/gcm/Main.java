package mod.gcm;

import mod.gcm.blocks.PickaxeBlock;
import mod.gcm.effects.LetOtherSeeYouStatusEffect;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BlockItem;
import net.minecraft.potion.Potion;

import static net.minecraft.registry.Registries.*;
import static net.minecraft.registry.Registry.register;

public class Main implements ModInitializer {
	public static Block PICKAXE_BLOCK =
			new PickaxeBlock(FabricBlockSettings.copy(Blocks.STONE));
	public static StatusEffect LET_OTHER_SEE_YOU_EFFECT =
			new LetOtherSeeYouStatusEffect(StatusEffectCategory.NEUTRAL, 0x3fad5b);
	public static Potion LET_OTHER_SEE_YOU_POTION =
			new Potion(new StatusEffectInstance(LET_OTHER_SEE_YOU_EFFECT, 1200));

	@Override
	public void onInitialize() {
		// 物品
		// 方块/方块物品
		register(BLOCK, "gcm:pickaxe_block", PICKAXE_BLOCK);
		register(ITEM, "gcm:pickaxe_block", new BlockItem(PICKAXE_BLOCK, new FabricItemSettings()));
		// 实体（未来可能不会有了）
		// 附魔
		// 状态效果/药水
		register(STATUS_EFFECT, "gcm:let_other_see_you", LET_OTHER_SEE_YOU_EFFECT);
		register(POTION, "gcm:let_other_see_you", LET_OTHER_SEE_YOU_POTION);
	}
}