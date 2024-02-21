package net.create.caffeinated.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class WitheringRackBlockEntity extends BlockEntity {
    private final DefaultedList<ItemStack> itemsBeingWithered = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final int[] witheringTimes = new int[4];
    private final int[] witheringTotalTimes = new int[4];


    public WitheringRackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WITHERING_RACK_BLOCK_ENTITY, pos, state);
    }

    public boolean addItem(@Nullable Entity user, ItemStack stack, int witherTime) {
        for (int i = 0; i < this.itemsBeingWithered.size(); ++i) {
            ItemStack itemStack = this.itemsBeingWithered.get(i);
            if (!itemStack.isEmpty()) continue;
            this.witheringTotalTimes[i] = witherTime;
            this.witheringTimes[i] = 0;
            this.itemsBeingWithered.set(i, stack.split(1));
            this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(user, this.getCachedState()));
            this.updateListeners();
            return true;
        }
        return false;
    }

    public DefaultedList<ItemStack> getItemsBeingWithered() {
        return this.itemsBeingWithered;
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }
}
