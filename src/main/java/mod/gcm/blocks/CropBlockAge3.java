package mod.gcm.blocks;

import mod.gcm.GHelper;
import mod.gcm.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

import static net.minecraft.state.property.Properties.AGE_3;

public class CropBlockAge3 extends CropBlock {
    public CropBlockAge3(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(AGE_3, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE_3);
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE_3;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return GHelper.getItem("gcm:pepper");
    }
}
