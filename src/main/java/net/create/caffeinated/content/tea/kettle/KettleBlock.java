package net.create.caffeinated.content.tea.kettle;

import com.simibubi.create.foundation.block.IBE;

import net.create.caffeinated.CreateCaffeinatedMod;
import net.create.caffeinated.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class KettleBlock extends HorizontalDirectionalBlock implements IBE<KettleBlockEntity> {
	public static final BooleanProperty support = BooleanProperty.create("support");

	public KettleBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(support, false));
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
		if (level.getBlockState(pos.below()).getBlock() instanceof CampfireBlock) {
			CreateCaffeinatedMod.LOGGER.info("Kettle is supported");
			level.setBlockAndUpdate(pos, state.setValue(support, true));
		} else {
			CreateCaffeinatedMod.LOGGER.info("Kettle isn't supported");
			level.setBlockAndUpdate(pos, state.setValue(support, false));
			support.value(false);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
		builder.add(support);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = this.defaultBlockState();
		return state.setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public Class<KettleBlockEntity> getBlockEntityClass() {
		return KettleBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends KettleBlockEntity> getBlockEntityType() {
		return ModBlockEntities.KETTLE.get();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.box(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.5D, 0.8125D);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		// Define the collision shape here
		return Shapes.box(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.5D, 0.8125D); // Example shape
	}
}
