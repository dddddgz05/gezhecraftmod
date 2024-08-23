package mod.gcm;

import mod.gcm.blocks.CropBlockAge3;
import mod.gcm.blocks.PickaxeBlock;
import mod.gcm.effects.LetOtherSeeYouStatusEffect;
import mod.gcm.effects.SpicyStatusEffect;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.registry.Registries.*;
import static net.minecraft.registry.Registry.register;

public class Main implements ModInitializer {
	public static RegistryKey<DamageType> SPICY_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "spicy"));

	public static Block PICKAXE_BLOCK =
			new PickaxeBlock(FabricBlockSettings.copy(Blocks.STONE));
	public static StatusEffect LET_OTHER_SEE_YOU_EFFECT =
			new LetOtherSeeYouStatusEffect(StatusEffectCategory.NEUTRAL, 0x3fad5b);
	public static Potion LET_OTHER_SEE_YOU_POTION =
			new Potion(new StatusEffectInstance(LET_OTHER_SEE_YOU_EFFECT, 1200));
	public static StatusEffect SPICY_EFFECT =
			new SpicyStatusEffect(StatusEffectCategory.NEUTRAL, 0xd02020);
	public static Block PEPPER_BLOCK =
			new CropBlockAge3(FabricBlockSettings.copy(Blocks.WHEAT));
	public static Item PEPPER_ITEM =
			new AliasedBlockItem(PEPPER_BLOCK, new FabricItemSettings().food(new FoodComponent.Builder()
					.saturationModifier(3).hunger(4).statusEffect(new StatusEffectInstance(SPICY_EFFECT, 1200), 0.9f)
					.build()));

	public static ItemGroup GROUP = FabricItemGroup.builder().icon(Items.SOUL_TORCH::getDefaultStack)
			.displayName(Text.translatable("itemGroup.gcm.group"))
			.entries((dispatcher, entries) -> {
				entries.add(new ItemStack(PICKAXE_BLOCK));
				entries.add(new ItemStack(PEPPER_ITEM));
			}).build();

	@Override
	public void onInitialize() {
		// 物品
		register(ITEM, "gcm:pepper", PEPPER_ITEM);
		// 物品组
		register(ITEM_GROUP, "gcm:group", GROUP);
		// 方块/方块物品
		register(BLOCK, "gcm:pepper", PEPPER_BLOCK);
		register(BLOCK, "gcm:pickaxe_block", PICKAXE_BLOCK);
		register(ITEM, "gcm:pickaxe_block", new BlockItem(PICKAXE_BLOCK, new FabricItemSettings()));
		// 实体（未来可能不会有了）
		// 附魔
		// 状态效果/药水
		register(STATUS_EFFECT, "gcm:let_other_see_you", LET_OTHER_SEE_YOU_EFFECT);
		register(POTION, "gcm:let_other_see_you", LET_OTHER_SEE_YOU_POTION);
		register(STATUS_EFFECT, "gcm:spicy", SPICY_EFFECT);
	}
}