package net.create.caffeinated.datagen;

import net.create.caffeinated.fluid.ModFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.create.caffeinated.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHERED_TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.TEA_CUP, Models.GENERATED);
        itemModelGenerator.register(ModFluids.GREEN_TEA_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModFluids.BLACK_TEA_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModFluids.WHITE_TEA_BUCKET, Models.GENERATED);
    }
}