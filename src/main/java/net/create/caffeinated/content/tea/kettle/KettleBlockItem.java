package net.create.caffeinated.content.tea.kettle;


import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class KettleBlockItem extends BlockItem {
	public KettleBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof CampfireBlock) {
			BlockState newState = this.getBlock().defaultBlockState().setValue(KettleBlock.FACING, context.getHorizontalDirection().getOpposite());
			newState = newState.setValue(KettleBlock.support, true);
			context.getLevel()
					.playSound(null, context.getClickedPos(), SoundEvents.COPPER_PLACE, SoundSource.BLOCKS, 1, 1);
			level.setBlockAndUpdate(pos.above(), newState);
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}
}
