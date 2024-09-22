package mod.gcm.utils;

import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;

public abstract class MyBlockUtil {
    public static int getMiningLevel(BlockState state) {
        if (state.isIn(BlockTags.NEEDS_STONE_TOOL)) {
            return 1;
        } else if (state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
            return 2;
        } else if (state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return 3;
        }
        return 0;
    }
}
