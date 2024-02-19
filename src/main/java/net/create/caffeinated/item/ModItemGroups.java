package net.create.caffeinated.item;

import net.create.caffeinated.block.ModBlocks;
import net.create.caffeinated.fluid.ModFluids;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.create.caffeinated.CreateCaffeinated;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(CreateCaffeinated.MOD_ID, "ccaffeinated"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.create_caffeinated"))
                    .icon(() -> new ItemStack(ModItems.TEA_CUP)).entries((displayContext, entries) -> {
                        entries.add(ModItems.TEA_LEAVES);
                        entries.add(ModItems.WITHERED_TEA_LEAVES);
                        entries.add(ModItems.DRIED_TEA_LEAVES);
                        entries.add(ModItems.TEA_CUP);

                        entries.add(ModFluids.GREEN_TEA_BUCKET);
                        entries.add(ModFluids.BLACK_TEA_BUCKET);
                        entries.add(ModFluids.WHITE_TEA_BUCKET);

                        entries.add(ModBlocks.TEA_RACK);
                    }).build());

    public static void registerItemGroups() {
        CreateCaffeinated.LOGGER.info("Registering Item Groups for " + CreateCaffeinated.MOD_ID);
    }
}