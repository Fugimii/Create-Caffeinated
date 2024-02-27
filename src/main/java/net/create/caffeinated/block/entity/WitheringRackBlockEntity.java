package net.create.caffeinated.block.entity;

import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class WitheringRackBlockEntity extends BlockEntity {
    private final DefaultedList<ItemStack> itemsBeingWithered = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final int[] witheringTimes = new int[4];
    private final int[] witheringTotalTimes = new int[4];

    public static void serverTick(World world, BlockPos pos, BlockState state, WitheringRackBlockEntity witheringRack) {
        boolean bl = false;
        for (int i = 0; i < witheringRack.itemsBeingWithered.size(); ++i) { // Loop through all items
            ItemStack itemStack = witheringRack.itemsBeingWithered.get(i);
            if (itemStack.isEmpty()) continue; // ignore if there isn't an item
            bl = true; // mojank variable name
            int n = i;
            witheringRack.witheringTimes[n] = witheringRack.witheringTimes[n] + 1; // withering item by one

            if (witheringRack.witheringTimes[n] >= witheringRack.witheringTotalTimes[n]) { // If withering is finished
//                ItemScatterer.spawn(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), ModItems.WITHERED_TEA_LEAVES.getDefaultStack());
                witheringRack.itemsBeingWithered.set(i, ModItems.WITHERED_TEA_LEAVES.getDefaultStack());
                witheringRack.witheringTotalTimes[n] = Integer.MAX_VALUE; // Kinda hacky tbh, oh well
                world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
            }
        }
        if (bl) {
            WitheringRackBlockEntity.markDirty(world, pos, state);
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

    public boolean dropItems(@Nullable Entity user) {
        for (int i = 0; i < this.itemsBeingWithered.size(); ++i) {
            ItemStack itemStack = this.itemsBeingWithered.get(i);

            // If the stack is empty \or it's not a withered item just ignore it
            if (!itemStack.isEmpty()) continue;

            this.itemsBeingWithered.set(i, ItemStack.EMPTY);

            ItemScatterer.spawn(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), ModItems.WITHERED_TEA_LEAVES.getDefaultStack()); // Drop the item
            CreateCaffeinated.LOGGER.info(String.valueOf(this.itemsBeingWithered.get(i)));

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

    @Override
    public void readNbt(NbtCompound nbt) {
        int[] is;
        super.readNbt(nbt);
        this.itemsBeingWithered.clear();
        Inventories.readNbt(nbt, this.itemsBeingWithered);
        if (nbt.contains("WitheringTimes", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("WitheringTimes");
            System.arraycopy(is, 0, this.witheringTimes, 0, Math.min(this.witheringTotalTimes.length, is.length));
        }
        if (nbt.contains("WitheringTotalTimes", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("WitheringTotalTimes");
            System.arraycopy(is, 0, this.witheringTotalTimes, 0, Math.min(this.witheringTotalTimes.length, is.length));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.itemsBeingWithered, true);
        nbt.putIntArray("WitheringTimes", this.witheringTimes);
        nbt.putIntArray("WitheringTotalTimes", this.witheringTotalTimes);
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        Inventories.writeNbt(nbtCompound, this.itemsBeingWithered, true);
        return nbtCompound;
    }
}
