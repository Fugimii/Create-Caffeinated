package net.create.caffeinated.block.custom;

import net.create.caffeinated.block.entity.WitheringRackBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        WitheringRackBlockEntity witheringRackBlockEntity;
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof WitheringRackBlockEntity) {
            witheringRackBlockEntity = ((WitheringRackBlockEntity) blockEntity);

            witheringRackBlockEntity.addItem(player, playerItem, 100);
        }

        return super.onUse(state, world, pos, player, hand, hit);
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
