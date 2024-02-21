package net.create.caffeinated.block.entity;

import net.create.caffeinated.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WitheringRackBlockEntity extends BlockEntity {
    private final DefaultedList<ItemStack> itemsBeingWithered = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final int[] witheringTimes = new int[4];
    private final int[] witheringTotalTimes = new int[4];

    public static void serverTick(World world, BlockPos pos, BlockState state, WitheringRackBlockEntity witheringRack) {
        boolean bl = false;
        for (int i = 0; i < witheringRack.itemsBeingWithered.size(); ++i) {
            ItemStack itemStack = witheringRack.itemsBeingWithered.get(i);
            if (itemStack.isEmpty()) continue;
            bl = true;
            int n = i;
            witheringRack.witheringTimes[n] = witheringRack.witheringTimes[n] + 1;

            if (witheringRack.witheringTimes[n] >= witheringRack.witheringTotalTimes[n]) {
                ItemScatterer.spawn(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), ModItems.WITHERED_TEA_LEAVES.getDefaultStack());
                witheringRack.itemsBeingWithered.set(i, ItemStack.EMPTY);
                world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
            }
        }
        if (bl) {
            CampfireBlockEntity.markDirty(world, pos, state);
        }
    }


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
