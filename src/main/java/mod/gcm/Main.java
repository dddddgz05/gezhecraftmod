package mod.gcm;

import mod.gcm.blocks.*;
import mod.gcm.blocks.entities.*;
import mod.gcm.commands.*;
import mod.gcm.items.*;
import mod.gcm.screen.*;
import mod.gcm.screen.handler.*;
import mod.gcm.utils.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.registry.Registries.*;
import static net.minecraft.registry.Registry.register;

public class Main implements ModInitializer {
	public static RegistryKey<DamageType> SPICY_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "spicy"));
	public static RegistryKey<DamageType> HOPPER_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("gcm", "hopper"));

	public static Block BETTER_FURNACE = new BetterFurnaceBlock(FabricBlockSettings.copy(Blocks.STONE));
	public static Block CHAT_BLOCK = new ChatBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
	public static Block EXPERIENCE_BLOCK = new ExperienceBlock(FabricBlockSettings.copy(Blocks.STONE));
	public static Block PEPPER_BLOCK = new CropBlockAge3(FabricBlockSettings.copy(Blocks.WHEAT));
	public static BlockEntityType<BetterFurnaceEntity> BETTER_FURNACE_TYPE = FabricBlockEntityTypeBuilder
			.create(BetterFurnaceEntity::new, BETTER_FURNACE).build();
	public static Item PEPPER_ITEM =
			new AliasedBlockItem(PEPPER_BLOCK, new FabricItemSettings().food(new FoodComponent.Builder()
					.saturationModifier(3).hunger(4).statusEffect(new StatusEffectInstance(GStatusEffects.SPICY, 1200, 2), 0.9f)
					.build()));
	public static Item EMERALD_BUCKET = new EmeraldBucketItem(new FabricItemSettings());
	public static Item EXPERIENCE = new ExperienceItem(new FabricItemSettings());
	public static final ScreenHandlerType<BetterFurnaceHandler> BETTER_FURNACE_HANDLER;
	static {
		BETTER_FURNACE_HANDLER = ScreenHandlerRegistry.registerSimple(Identifier.of("gcm", "better_furnace"), BetterFurnaceHandler::new);
		HandledScreens.register(BETTER_FURNACE_HANDLER, BetterFurnaceScreen::new);
	}

	public static ItemGroup GROUP = FabricItemGroup.builder().icon(Items.SOUL_CAMPFIRE::getDefaultStack)
			.displayName(Text.translatable("itemGroup.gcm.group"))
			.entries((dispatcher, entries) -> {
				entries.add(new ItemStack(CHAT_BLOCK));
				entries.add(new ItemStack(EXPERIENCE_BLOCK));
				entries.add(new ItemStack(EMERALD_BUCKET));
				entries.add(new ItemStack(EXPERIENCE));
				entries.add(new ItemStack(PEPPER_ITEM));
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
		// 物品
		register(ITEM, "gcm:pepper", PEPPER_ITEM);
		register(ITEM, "gcm:emerald_bucket", EMERALD_BUCKET);
		register(ITEM, "gcm:experience", EXPERIENCE);
		// 物品组
		register(ITEM_GROUP, "gcm:group", GROUP);
		// 方块/方块物品/方块实体类型
		register(BLOCK, "gcm:pepper", PEPPER_BLOCK);
		register(BLOCK, "gcm:better_furnace", BETTER_FURNACE);
		register(ITEM, "gcm:better_furnace", new BlockItem(BETTER_FURNACE, new FabricItemSettings()));
		register(BLOCK, "gcm:chat_block", CHAT_BLOCK);
		register(ITEM, "gcm:chat_block", new BlockItem(CHAT_BLOCK, new FabricItemSettings()));
		register(BLOCK, "gcm:experience_block", EXPERIENCE_BLOCK);
		register(ITEM, "gcm:experience_block", new BlockItem(EXPERIENCE_BLOCK, new FabricItemSettings()));
		register(BLOCK_ENTITY_TYPE, "gcm:better_furnace", BETTER_FURNACE_TYPE);
		GBlocks.register();
		GEnchantments.register();
		GStatusEffects.register();
	}
}