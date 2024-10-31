package net.create.caffeinated.content;

import com.tterrag.registrate.util.entry.BlockEntry;

import net.create.caffeinated.CreateCaffeinatedMod;
import net.create.caffeinated.content.tea.kettle.KettleBlock;
import net.create.caffeinated.content.tea.kettle.KettleBlockItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;

import static net.create.caffeinated.CreateCaffeinatedMod.REGISTRATE;

public class ModBlocks {

	public static final BlockEntry<KettleBlock> KETTLE = REGISTRATE.block("kettle", KettleBlock::new)
			.initialProperties(() -> Blocks.LIGHTNING_ROD)
			.lang("Kettle")
			.addLayer(() -> RenderType::cutout)
			// we have a custom blockstate so there is no need
			.blockstate((ctx, prov) -> {})
			.item(KettleBlockItem::new)
			.build()
			.register();

	public static void register() {
		CreateCaffeinatedMod.LOGGER.info("Registering Blocks for " + CreateCaffeinatedMod.MOD_ID);
	}
}
