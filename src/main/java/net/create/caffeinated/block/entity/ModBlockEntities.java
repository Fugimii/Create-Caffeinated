package net.create.caffeinated.block.entity;

import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<DryingRackBlockEntity> DRYING_RACK_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(CreateCaffeinated.MOD_ID, "drying_rack_be"),
                    FabricBlockEntityTypeBuilder.create(DryingRackBlockEntity::new,
                            ModBlocks.DRYING_RACK).build());

    public static void registerBlockEntities() {
        CreateCaffeinated.LOGGER.info("Registering Block Entities for " + CreateCaffeinated.MOD_ID);
    }
}