package net.create.caffeinated.item;

import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEA_LEAVES = registerItem("tea_leaves", new AliasedBlockItem(ModBlocks.TEA_BUSH, new Item.Settings()));
    public static final Item WITHERED_TEA_LEAVES = registerItem("withered_tea_leaves", new Item(new FabricItemSettings()));
    public static final Item TEA_CUP = registerItem("tea_cup", new Item(new FabricItemSettings().maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CreateCaffeinated.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CreateCaffeinated.LOGGER.info("Registering Items for " + CreateCaffeinated.MOD_ID);
    }
}