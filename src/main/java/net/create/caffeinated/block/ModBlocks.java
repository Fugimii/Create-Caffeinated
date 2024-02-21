package net.create.caffeinated.block;

import net.create.caffeinated.block.custom.TeaBushBlock;
import net.create.caffeinated.block.custom.WitheringRackBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.create.caffeinated.CreateCaffeinated;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TEA_BUSH = registerBlock("tea_bush",
            new TeaBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));

    public static final Block DRYING_RACK = registerBlockWithItem("drying_rack",
            new WitheringRackBlock(FabricBlockSettings.copyOf(Blocks.SCAFFOLDING).nonOpaque().collidable(true)));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(CreateCaffeinated.MOD_ID, name), block);
    }
    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(CreateCaffeinated.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(CreateCaffeinated.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        CreateCaffeinated.LOGGER.info("Registering ModBlocks for " + CreateCaffeinated.MOD_ID);
    }
}