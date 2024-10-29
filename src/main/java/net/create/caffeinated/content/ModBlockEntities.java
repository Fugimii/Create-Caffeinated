package net.create.caffeinated.content;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import net.create.caffeinated.CreateCaffeinatedMod;
import net.create.caffeinated.content.tea.kettle.KettleBlockEntity;

import static net.create.caffeinated.CreateCaffeinatedMod.REGISTRATE;

public class ModBlockEntities {
	public static final BlockEntityEntry<KettleBlockEntity> KETTLE = REGISTRATE
			.blockEntity("calibrated_solar_panel", KettleBlockEntity::new)
			.validBlocks(ModBlocks.KETTLE)
			.register();

	public static void register() {
		CreateCaffeinatedMod.LOGGER.info("Registering Block Entities for " + CreateCaffeinatedMod.MOD_ID);
	}
}
