package net.create.caffeinated;

import com.simibubi.create.Create;

import com.simibubi.create.foundation.data.CreateRegistrate;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.create.caffeinated.content.ModBlockEntities;
import net.create.caffeinated.content.ModBlocks;
import net.create.caffeinated.content.ModCreativeModTabs;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCaffeinatedMod implements ModInitializer {
	public static final String MOD_ID = "caffeinated";
	public static final String NAME = "Create Caffeinated";
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(
				() -> () -> "{} is accessing Porting Lib from the client!",
				() -> () -> "{} is accessing Porting Lib from the server!"
		), NAME);

		ModBlocks.register();
		ModBlockEntities.register();
		ModCreativeModTabs.register();
		REGISTRATE.register();
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
