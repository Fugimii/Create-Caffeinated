package net.create.caffeinated;

import net.create.caffeinated.block.ModBlocks;
import net.create.caffeinated.block.entity.ModArmInteractionPoints;
import net.create.caffeinated.block.entity.ModBlockEntities;
import net.create.caffeinated.fluid.ModFluids;
import net.create.caffeinated.item.ModItemGroups;
import net.create.caffeinated.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCaffeinated implements ModInitializer {
	public static final String MOD_ID = "ccaffeinated";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModFluids.registerModFluids();
		ModArmInteractionPoints.registerArmInteractionPoints();
	}
}