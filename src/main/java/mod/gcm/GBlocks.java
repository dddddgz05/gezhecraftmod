package mod.gcm;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.item.BlockItem;
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

    public static void block(Block block, String path) {
        Registry.register(Registries.BLOCK, Identifier.of("gcm", path), block);
        Registry.register(Registries.ITEM, Identifier.of("gcm", path), new BlockItem(block, new FabricItemSettings()));
    }

    public static void register() {
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
    }
}
