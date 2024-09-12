package mod.gcm.blocks.entities;

import mod.gcm.Main;
import mod.gcm.screen.handler.BetterFurnaceHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class BetterFurnaceEntity extends LockableContainerBlockEntity {
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(3, ItemStack.EMPTY);
    public BetterFurnaceEntity(BlockPos blockPos, BlockState blockState) {
        super(Main.BETTER_FURNACE_TYPE, blockPos, blockState);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.gcm.better_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BetterFurnaceHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return inv.size();
    }

    @Override
    public boolean isEmpty() {
        return inv.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return inv.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        inv.get(slot).decrement(amount);
        return inv.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return inv.remove(slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inv.set(slot, stack);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        inv.clear();
    }
}
