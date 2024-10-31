package net.create.caffeinated.content;

import net.create.caffeinated.CreateCaffeinatedMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTabs {
	public static final CreativeModeTab CREATIVE_MODE_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
			new ResourceLocation(CreateCaffeinatedMod.MOD_ID, "caffeinated"),
			FabricItemGroup.builder()
					.title(Component.translatable("itemGroup.caffeinated"))
					.icon(() -> new ItemStack(ModBlocks.KETTLE))
					.displayItems((pParameters, entries) -> {

						entries.accept(ModBlocks.KETTLE);

					}).build());

	public static void register() {
		CreateCaffeinatedMod.LOGGER.info("Registering Creative Mod Tabs for " + CreateCaffeinatedMod.MOD_ID);
	}
}
