package net.create.caffeinated.content.tea.kettle;

import com.simibubi.create.foundation.block.IBE;

import net.create.caffeinated.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class KettleBlock extends HorizontalDirectionalBlock implements IBE<KettleBlockEntity> {

	public KettleBlock(Properties properties) {
		super(properties);
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
		return Shapes.box(0.2D, 0.0D, 0.2D, 0.8D, 0.625D, 0.8D);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		// Define the collision shape here
		return Shapes.box(0.2D, 0.0D, 0.2D, 0.8D, 0.625D, 0.8D); // Example shape
	}
}
