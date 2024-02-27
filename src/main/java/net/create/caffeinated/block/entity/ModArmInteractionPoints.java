package net.create.caffeinated.block.entity;

import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.mechanicalArm.AllArmInteractionPointTypes;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;
import io.github.fabricators_of_create.porting_lib.transfer.callbacks.TransactionCallback;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemHandlerHelper;
import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.block.custom.WitheringRackBlock;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.Function;

public class ModArmInteractionPoints {
    public static final ModArmInteractionPoints.WitheringRackType WITHERING_RACK = register("withering_rack", WitheringRackType::new);

    private static <T extends ArmInteractionPointType> T register(String id, Function<Identifier, T> factory) {
        T type = factory.apply(Create.asResource(id));
        ArmInteractionPointType.register(type);
        return type;
    }

    public static void registerArmInteractionPoints() {
        CreateCaffeinated.LOGGER.info("Registering Mod Interaction Points for " + CreateCaffeinated.MOD_ID);
    }

    public static class WitheringRackType extends ArmInteractionPointType {
        public WitheringRackType(Identifier id) {
            super(id);
        }

        @Override
        public boolean canCreatePoint(World level, BlockPos pos, BlockState state) {
            return state.getBlock() instanceof WitheringRackBlock;
        }

        @Override
        public ArmInteractionPoint createPoint(World level, BlockPos pos, BlockState state) {
            return new WitheringRackPoint(this, level, pos, state);
        }
    }

    public static class WitheringRackPoint extends AllArmInteractionPointTypes.DepositOnlyArmInteractionPoint {
        public WitheringRackPoint(ArmInteractionPointType type, World level, BlockPos pos, BlockState state) {
            super(type, level, pos, state);
        }

        @Override
        public ItemStack insert(ItemStack stack, TransactionContext ctx) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof WitheringRackBlockEntity witheringRackBE))
                return stack;
//            Optional<CampfireCookingRecipe> recipe = witheringRackBE.getRecipeFor(stack);
//            if (recipe.isEmpty())
//                return stack;
            boolean hasSpace = false;
            for (ItemStack campfireStack : witheringRackBE.getItemsBeingWithered()) {
                if (campfireStack.isEmpty()) {
                    hasSpace = true;
                    break;
                }
            }
            if (!hasSpace)
                return stack;
            ItemStack inserted = ItemHandlerHelper.copyStackWithSize(stack, 1);
//            TransactionCallback.onSuccess(ctx, () -> witheringRackBE.addItem(null, inserted, recipe.get().getCookTime()));
            TransactionCallback.onSuccess(ctx, () -> witheringRackBE.addItem(null, inserted, 100));
            ItemStack remainder = stack.copy();
            remainder.decrement(1);
            return remainder;
        }
    }
}
