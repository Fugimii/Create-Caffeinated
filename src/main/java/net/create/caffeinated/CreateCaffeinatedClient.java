package net.create.caffeinated;

import net.create.caffeinated.block.ModBlocks;
import net.create.caffeinated.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class CreateCaffeinatedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEA_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEA_BUSH, RenderLayer.getCutout());

        // Green Tea
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_GREEN_TEA, ModFluids.FLOWING_GREEN_TEA);

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GREEN_TEA, ModFluids.FLOWING_GREEN_TEA,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0x609816
                ));

        // Black Tea
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_BLACK_TEA, ModFluids.FLOWING_BLACK_TEA);

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLACK_TEA, ModFluids.FLOWING_BLACK_TEA,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0x570800
                ));

        // White Tea
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_WHITE_TEA, ModFluids.FLOWING_WHITE_TEA);

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_WHITE_TEA, ModFluids.FLOWING_WHITE_TEA,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xc2c2c2
                ));

    }
}