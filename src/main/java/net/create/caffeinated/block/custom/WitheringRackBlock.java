package net.create.caffeinated.block.custom;

import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.block.entity.ModBlockEntities;
import net.create.caffeinated.block.entity.WitheringRackBlockEntity;
import net.create.caffeinated.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
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

        if (!item.isOf(ModItems.TEA_LEAVES)) { // Check if player is holding tea leaves
            return ActionResult.PASS;
        }

        // Check if the player clicked on a withering rack
        if (!(world.getBlockEntity(pos) instanceof WitheringRackBlockEntity)) {
            return ActionResult.PASS;
        }

        // Get the withering rack block entity
        WitheringRackBlockEntity witheringRack = (WitheringRackBlockEntity) world.getBlockEntity(pos);

        // Try to add the item to the withering rack
        if (!world.isClient && witheringRack.addItem(player, item.copy(), 100) && item.isOf(ModItems.TEA_LEAVES)) {
            return ActionResult.SUCCESS;
        }

        witheringRack.dropItems(player);

        if (!world.isClient) {

            CreateCaffeinated.LOGGER.info("Clicked");

            return ActionResult.SUCCESS;
        }

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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!world.isClient) {
            BlockEntityType<WitheringRackBlockEntity> WitheringRackBlockEntity;
            return WitheringRackBlock.checkType(type, ModBlockEntities.WITHERING_RACK_BLOCK_ENTITY, net.create.caffeinated.block.entity.WitheringRackBlockEntity::serverTick);
        }
        return null;
    }
}
