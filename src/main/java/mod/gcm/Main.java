package mod.gcm;

import mod.gcm.blocks.ChatBlock;
import mod.gcm.blocks.ExperienceBlock;
import mod.gcm.commands.GetEnchantments;
import mod.gcm.commands.GetNbt;
import mod.gcm.commands.KeepInventory;
import mod.gcm.commands.TeleportToDeath;
import mod.gcm.trades.VillagerNuggetTrade;
import mod.gcm.utils.PickaxeDispenserBehavior;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.registry.Registries.*;
import static net.minecraft.registry.Registry.register;

public class Main implements ModInitializer {
	public static RegistryKey<DamageType> SPICY_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "spicy"));
	public static RegistryKey<DamageType> HOPPER_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "hopper"));

	public static Block CHAT_BLOCK = new ChatBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
	public static Block EXPERIENCE_BLOCK = new ExperienceBlock(FabricBlockSettings.copy(Blocks.STONE));

	public static ItemGroup GROUP = FabricItemGroup.builder().icon(Items.SOUL_CAMPFIRE::getDefaultStack)
			.displayName(Text.translatable("itemGroup.gcm.group"))
			.entries((dispatcher, entries) -> {
				entries.add(new ItemStack(CHAT_BLOCK));
				entries.add(new ItemStack(EXPERIENCE_BLOCK));
				entries.add(new ItemStack(GItems.CALENDAR));
				entries.add(new ItemStack(GItems.EMERALD_BUCKET));
				entries.add(new ItemStack(GItems.EXPERIENCE));
				entries.add(new ItemStack(GHelper.getItem("gcm:pepper")));
				entries.add(new ItemStack(GItems.SPICIER_PEPPER));
				entries.add(new ItemStack(GItems.VILLAGER_NUGGET));
				entries.add(new ItemStack(GItems.VILLAGER_INGOT));
				entries.add(new ItemStack(GItems.VILLAGER_SWORD));
				entries.add(new ItemStack(GBlocks.LIGHT_GRAY_LAMP));
				entries.add(new ItemStack(GBlocks.GRAY_LAMP));
				entries.add(new ItemStack(GBlocks.BLACK_LAMP));
				entries.add(new ItemStack(GBlocks.BROWN_LAMP));
				entries.add(new ItemStack(GBlocks.RED_LAMP));
				entries.add(new ItemStack(GBlocks.ORANGE_LAMP));
				entries.add(new ItemStack(GBlocks.YELLOW_LAMP));
				entries.add(new ItemStack(GBlocks.LIME_LAMP));
				entries.add(new ItemStack(GBlocks.GREEN_LAMP));
				entries.add(new ItemStack(GBlocks.CYAN_LAMP));
				entries.add(new ItemStack(GBlocks.LIGHT_BLUE_LAMP));
				entries.add(new ItemStack(GBlocks.BLUE_LAMP));
				entries.add(new ItemStack(GBlocks.PURPLE_LAMP));
				entries.add(new ItemStack(GBlocks.MAGENTA_LAMP));
				entries.add(new ItemStack(GBlocks.PINK_LAMP));
			}).build();

	@Override
	public void onInitialize() {
		DispenserBlock.registerBehavior(Items.WOODEN_PICKAXE, new PickaxeDispenserBehavior());
		DispenserBlock.registerBehavior(Items.GOLDEN_PICKAXE, new PickaxeDispenserBehavior());
		DispenserBlock.registerBehavior(Items.STONE_PICKAXE, new PickaxeDispenserBehavior());
		DispenserBlock.registerBehavior(Items.IRON_PICKAXE, new PickaxeDispenserBehavior());
		DispenserBlock.registerBehavior(Items.DIAMOND_PICKAXE, new PickaxeDispenserBehavior());
		DispenserBlock.registerBehavior(Items.NETHERITE_PICKAXE, new PickaxeDispenserBehavior());
		// 命令
		TeleportToDeath.register();
		GetEnchantments.register();
		KeepInventory.register();
		GetNbt.register();
		GItems.register();
		// 物品组
		register(ITEM_GROUP, "gcm:group", GROUP);
		// 方块/方块物品
		register(BLOCK, "gcm:chat_block", CHAT_BLOCK);
		register(ITEM, "gcm:chat_block", new BlockItem(CHAT_BLOCK, new FabricItemSettings()));
		register(BLOCK, "gcm:experience_block", EXPERIENCE_BLOCK);
		register(ITEM, "gcm:experience_block", new BlockItem(EXPERIENCE_BLOCK, new FabricItemSettings()));
		GBlocks.register();
		GEnchantments.register();
		GStatusEffects.register();
		VillagerNuggetTrade.add();
	}
}