package mod.gcm;

import mod.gcm.commands.KeepInv;
import mod.gcm.commands.TpDeath;
import mod.gcm.utils.PickaxeDispenserBehavior;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static mod.gcm.GBlocks.*;
import static mod.gcm.GCommands.command;
import static mod.gcm.GEnchantments.enchantment;
import static mod.gcm.GItems.*;
import static mod.gcm.GParticles.particle;
import static mod.gcm.GStatusEffects.effect;
import static mod.gcm.GTrades.trade;
import static net.minecraft.block.DispenserBlock.registerBehavior;
import static net.minecraft.registry.Registries.ITEM_GROUP;
import static net.minecraft.registry.Registry.register;

public class Main implements ModInitializer {
	public static RegistryKey<DamageType> SPICY_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "spicy"));
	public static RegistryKey<DamageType> HOPPER_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "hopper"));

	public static ItemGroup GROUP = FabricItemGroup.builder().icon(Items.SOUL_CAMPFIRE::getDefaultStack)
			.displayName(Text.translatable("itemGroup.gcm.group"))
			.entries((dispatcher, entries) -> {
				entries.add(new ItemStack(CALENDAR));
				entries.add(new ItemStack(EMERALD_BUCKET));
//				entries.add(new ItemStack(EXPERIENCE));
				entries.add(new ItemStack(PEPPER));
				entries.add(new ItemStack(SPICIER_PEPPER));
				entries.add(new ItemStack(VILLAGER_NUGGET));
				entries.add(new ItemStack(VILLAGER_INGOT));
				entries.add(new ItemStack(VILLAGER_SWORD));
//				entries.add(new ItemStack(EXPERIENCE_BLOCK));
				entries.add(new ItemStack(CHAT_BLOCK));
				entries.add(new ItemStack(LIGHT_GRAY_LAMP));
				entries.add(new ItemStack(GRAY_LAMP));
				entries.add(new ItemStack(BLACK_LAMP));
				entries.add(new ItemStack(BROWN_LAMP));
				entries.add(new ItemStack(RED_LAMP));
				entries.add(new ItemStack(ORANGE_LAMP));
				entries.add(new ItemStack(YELLOW_LAMP));
				entries.add(new ItemStack(LIME_LAMP));
				entries.add(new ItemStack(GREEN_LAMP));
				entries.add(new ItemStack(CYAN_LAMP));
				entries.add(new ItemStack(LIGHT_BLUE_LAMP));
				entries.add(new ItemStack(BLUE_LAMP));
				entries.add(new ItemStack(PURPLE_LAMP));
				entries.add(new ItemStack(MAGENTA_LAMP));
				entries.add(new ItemStack(PINK_LAMP));
			}).build();

	@Override
	public void onInitialize() {
		registerBehavior(Items.WOODEN_PICKAXE, new PickaxeDispenserBehavior());
		registerBehavior(Items.GOLDEN_PICKAXE, new PickaxeDispenserBehavior());
		registerBehavior(Items.STONE_PICKAXE, new PickaxeDispenserBehavior());
		registerBehavior(Items.IRON_PICKAXE, new PickaxeDispenserBehavior());
		registerBehavior(Items.DIAMOND_PICKAXE, new PickaxeDispenserBehavior());
		registerBehavior(Items.NETHERITE_PICKAXE, new PickaxeDispenserBehavior());
		command();
		TpDeath.register();
		KeepInv.register();
		item();
		block();
		effect();
		enchantment();
		particle();
		trade();
		register(ITEM_GROUP, "gcm:group", GROUP);
	}
}