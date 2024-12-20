package mod.gcm;

import mod.gcm.blocks.ChatBlock;
import mod.gcm.blocks.PepperBlock;
import mod.gcm.blocks.ExperienceBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GBlocks {
    public static Block WHITE_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block LIGHT_GRAY_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block GRAY_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block BLACK_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block BROWN_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block RED_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block ORANGE_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block YELLOW_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block LIME_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block GREEN_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block CYAN_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block LIGHT_BLUE_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block BLUE_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block PURPLE_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block MAGENTA_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block PINK_LAMP = new RedstoneLampBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP));
    public static Block PEPPER_BLOCK = new PepperBlock(FabricBlockSettings.copy(Blocks.WHEAT));
    public static Block CHAT_BLOCK = new ChatBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
    public static Block EXPERIENCE_BLOCK = new ExperienceBlock(FabricBlockSettings.copy(Blocks.STONE));

    public static void block(Block block, String path) {
        noItemBlock(block, path);
        Registry.register(Registries.ITEM, Identifier.of("gcm", path), new BlockItem(block, new FabricItemSettings()));
    }

    public static void noItemBlock(Block block, String path) {
        Registry.register(Registries.BLOCK, Identifier.of("gcm", path), block);
    }

    public static void block() {
        block(WHITE_LAMP, "white_lamp");
        block(LIGHT_GRAY_LAMP, "light_gray_lamp");
        block(GRAY_LAMP, "gray_lamp");
        block(BLACK_LAMP, "black_lamp");
        block(BROWN_LAMP, "brown_lamp");
        block(RED_LAMP, "red_lamp");
        block(ORANGE_LAMP, "orange_lamp");
        block(YELLOW_LAMP, "yellow_lamp");
        block(LIME_LAMP, "lime_lamp");
        block(GREEN_LAMP, "green_lamp");
        block(CYAN_LAMP, "cyan_lamp");
        block(LIGHT_BLUE_LAMP, "light_blue_lamp");
        block(BLUE_LAMP, "blue_lamp");
        block(PURPLE_LAMP, "purple_lamp");
        block(MAGENTA_LAMP, "magenta_lamp");
        block(PINK_LAMP, "pink_lamp");
        block(CHAT_BLOCK, "chat_block");
        block(EXPERIENCE_BLOCK, "experience_block");
        noItemBlock(PEPPER_BLOCK, "pepper");
    }
}
