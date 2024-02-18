package net.create.caffeinated.fluid;

import net.create.caffeinated.CreateCaffeinated;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static FlowableFluid STILL_GREEN_TEA;
    public static FlowableFluid FLOWING_GREEN_TEA;
    public static Block GREEN_TEA_BLOCK;
    public static Item GREEN_TEA_BUCKET;

    public static FlowableFluid STILL_BLACK_TEA;
    public static FlowableFluid FLOWING_BLACK_TEA;
    public static Block BLACK_TEA_BLOCK;
    public static Item BLACK_TEA_BUCKET;

    public static FlowableFluid STILL_WHITE_TEA;
    public static FlowableFluid FLOWING_WHITE_TEA;
    public static Block WHITE_TEA_BLOCK;
    public static Item WHITE_TEA_BUCKET;

    public static void registerModFluids() {
        STILL_GREEN_TEA = Registry.register(Registries.FLUID, new Identifier(CreateCaffeinated.MOD_ID, "green_tea"), new GreenTeaFluid.Still());
        FLOWING_GREEN_TEA = Registry.register(Registries.FLUID, new Identifier(CreateCaffeinated.MOD_ID, "flowing_green_tea"), new GreenTeaFluid.Flowing());
        GREEN_TEA_BUCKET = Registry.register(Registries.ITEM, new Identifier(CreateCaffeinated.MOD_ID, "green_tea_bucket"), new BucketItem(STILL_GREEN_TEA, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

        GREEN_TEA_BLOCK = Registry.register(Registries.BLOCK, new Identifier(CreateCaffeinated.MOD_ID, "green_tea_block"),
                new FluidBlock(STILL_GREEN_TEA, FabricBlockSettings.copy(Blocks.WATER)){});

        STILL_BLACK_TEA = Registry.register(Registries.FLUID, new Identifier(CreateCaffeinated.MOD_ID, "black_tea"), new BlackTeaFluid.Still());
        FLOWING_BLACK_TEA = Registry.register(Registries.FLUID, new Identifier(CreateCaffeinated.MOD_ID, "flowing_black_tea"), new BlackTeaFluid.Flowing());
        BLACK_TEA_BUCKET = Registry.register(Registries.ITEM, new Identifier(CreateCaffeinated.MOD_ID, "black_tea_bucket"), new BucketItem(STILL_BLACK_TEA, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

        BLACK_TEA_BLOCK = Registry.register(Registries.BLOCK, new Identifier(CreateCaffeinated.MOD_ID, "black_tea_block"),
                new FluidBlock(STILL_BLACK_TEA, FabricBlockSettings.copy(Blocks.WATER)){});

        STILL_WHITE_TEA = Registry.register(Registries.FLUID, new Identifier(CreateCaffeinated.MOD_ID, "white_tea"), new WhiteTeaFluid.Still());
        FLOWING_WHITE_TEA = Registry.register(Registries.FLUID, new Identifier(CreateCaffeinated.MOD_ID, "flowing_white_tea"), new WhiteTeaFluid.Flowing());
        WHITE_TEA_BUCKET = Registry.register(Registries.ITEM, new Identifier(CreateCaffeinated.MOD_ID, "white_tea_bucket"), new BucketItem(STILL_WHITE_TEA, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

        WHITE_TEA_BLOCK = Registry.register(Registries.BLOCK, new Identifier(CreateCaffeinated.MOD_ID, "white_tea_block"),
                new FluidBlock(STILL_WHITE_TEA, FabricBlockSettings.copy(Blocks.WATER)){});
    }
}