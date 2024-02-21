package net.create.caffeinated.block.custom;

import net.create.caffeinated.block.entity.WitheringRackBlockEntity;
import net.create.caffeinated.item.ModItems;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WitheringRackBlock extends BlockWithEntity {

    public WitheringRackBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WitheringRackBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Get the item the player is holding
        ItemStack item = player.getStackInHand(hand);

        // Check if the player clicked on a withering rack
        if (!(world.getBlockEntity(pos) instanceof WitheringRackBlockEntity)) {
            return ActionResult.PASS;
        }

        // Get the withering rack block entity
        WitheringRackBlockEntity witheringRack = (WitheringRackBlockEntity) world.getBlockEntity(pos);

        // Check if the item can be cooked in the withering rack
        if (!item.isOf(ModItems.TEA_LEAVES)) {
            return ActionResult.PASS;
        }

        // Try to add the item to the campfire
        if (!world.isClient && witheringRack.addItem(player, item.copy(), 100)) {

            // Success! Return success.
            return ActionResult.CONSUME;
        }

        // The campfire might be full, so the item couldn't be added.
        return ActionResult.PASS;
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof WitheringRackBlockEntity) {
            ItemScatterer.spawn(world, pos, ((WitheringRackBlockEntity)blockEntity).getItemsBeingWithered());
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
