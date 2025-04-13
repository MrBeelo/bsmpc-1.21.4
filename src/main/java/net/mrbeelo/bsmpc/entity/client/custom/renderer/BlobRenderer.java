package net.mrbeelo.bsmpc.entity.client.custom.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.client.ModEntityModelLayers;
import net.mrbeelo.bsmpc.entity.client.custom.model.BlobModel;
import net.mrbeelo.bsmpc.entity.client.custom.model.SnekModel;
import net.mrbeelo.bsmpc.entity.client.custom.state.BlobEntityRenderState;
import net.mrbeelo.bsmpc.entity.custom.BlobEntity;

public class BlobRenderer extends MobEntityRenderer<BlobEntity, BlobEntityRenderState, BlobModel> {
    public static final Identifier TEXTURE = BsmpC.id("textures/entity/blob/blob.png");
    public static final Identifier TEXTURE_SADDLED = BsmpC.id("textures/entity/blob/blob_saddled.png");

    public BlobRenderer(EntityRendererFactory.Context context) {
        super(context, new BlobModel(context.getPart(ModEntityModelLayers.BLOB)), 0.5f);
    }

    @Override
    public BlobEntityRenderState createRenderState() {
        return new BlobEntityRenderState();
    }

    @Override
    public void updateRenderState(BlobEntity livingEntity, BlobEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);

        livingEntityRenderState.limbFrequency = livingEntity.limbAnimator.getPos();
        livingEntityRenderState.limbAmplitudeMultiplier = livingEntity.limbAnimator.getSpeed();

        livingEntityRenderState.baby = livingEntity.isBaby();
        livingEntityRenderState.isSaddled = livingEntity.isSaddled();
    }

    @Override
    public void render(BlobEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntityRenderState.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BlobEntityRenderState state) {
        if(state.isSaddled) {
            return TEXTURE_SADDLED;
        } else {
            return TEXTURE;
        }
    }
}
