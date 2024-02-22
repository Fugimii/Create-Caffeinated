package net.create.caffeinated.block.entity.render;

import net.create.caffeinated.CreateCaffeinated;
import net.create.caffeinated.block.custom.WitheringRackBlock;
import net.create.caffeinated.block.entity.WitheringRackBlockEntity;
import net.create.caffeinated.item.ModItems;
import net.minecraft.block.CampfireBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class WitheringRackEntityRenderer implements BlockEntityRenderer<WitheringRackBlockEntity> {

    public WitheringRackEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(WitheringRackBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        DefaultedList<ItemStack> items = entity.getItemsBeingWithered();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        float voxel_size = 1f/16f;
        for (int l = 0; l < items.size(); ++l) {
            ItemStack itemStack = items.get(l);

            matrices.push();
            float y_pos = voxel_size * (l * 4) + voxel_size;
            matrices.translate(0.5f, y_pos, 0.5f);
            matrices.scale(1f, 0.005f, 1f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));

            itemRenderer.renderItem(itemStack.getItem().getDefaultStack(), ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                    entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);

            matrices.pop();
        }
    }
    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
