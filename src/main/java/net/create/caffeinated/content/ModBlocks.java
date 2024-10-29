package net.create.caffeinated.content;

import com.tterrag.registrate.util.entry.BlockEntry;

import net.create.caffeinated.CreateCaffeinatedMod;
import net.create.caffeinated.content.tea.kettle.KettleBlock;
import net.minecraft.world.level.block.Blocks;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static net.create.caffeinated.CreateCaffeinatedMod.REGISTRATE;

public class ModBlocks {

	public static final BlockEntry<KettleBlock> KETTLE = REGISTRATE.block("kettle", KettleBlock::new)
			.initialProperties(() -> Blocks.LIGHTNING_ROD)
			.lang("Kettle")
			.blockstate((c, p) -> p.simpleBlock(c.getEntry(), // Is this the right way to do this???
					p.models().getExistingFile(p.modLoc("block/kettle")))
			)
			.item()
			.build()
			.register();

	public static void register() {
		CreateCaffeinatedMod.LOGGER.info("Registering Blocks for " + CreateCaffeinatedMod.MOD_ID);
	}
}
