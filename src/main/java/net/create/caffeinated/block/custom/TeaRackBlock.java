package net.create.caffeinated.block.custom;

import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeaRackBlock extends Block {
    public static final BooleanProperty FULL = BooleanProperty.of("full");

    public TeaRackBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FULL, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FULL);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isHolding(ModItems.TEA_LEAVES)) {
            world.setBlockState(pos, state.with(FULL, true));
            return ActionResult.CONSUME_PARTIAL;
        } else {
            return ActionResult.PASS;
        }

    }
}
