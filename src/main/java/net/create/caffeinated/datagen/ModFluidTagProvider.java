package net.create.caffeinated.datagen;

import net.create.caffeinated.fluid.ModFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.FluidTags;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagProvider.FluidTagProvider {
    public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(FluidTags.WATER)
                .add(ModFluids.STILL_GREEN_TEA)
                .add(ModFluids.FLOWING_GREEN_TEA)
                .add(ModFluids.STILL_BLACK_TEA)
                .add(ModFluids.FLOWING_BLACK_TEA)
                .add(ModFluids.STILL_WHITE_TEA)
                .add(ModFluids.FLOWING_WHITE_TEA);
    }
}