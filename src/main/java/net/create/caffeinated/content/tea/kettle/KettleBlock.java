package net.create.caffeinated.content.tea.kettle;

import com.simibubi.create.foundation.block.IBE;

import net.create.caffeinated.content.ModBlockEntities;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

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
}
