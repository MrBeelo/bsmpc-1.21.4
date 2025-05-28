package net.mrbeelo.bsmpc.entity.client.custom.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.client.ModEntityModelLayers;
import net.mrbeelo.bsmpc.entity.client.custom.model.PyroModel;
import net.mrbeelo.bsmpc.entity.client.custom.state.PyroEntityRenderState;
import net.mrbeelo.bsmpc.entity.custom.PyroEntity;
import net.mrbeelo.bsmpc.entity.custom.SnekEntity;

public class PyroRenderer extends MobEntityRenderer<PyroEntity, PyroEntityRenderState, PyroModel> {
    public static final Identifier TEXTURE = BsmpC.id("textures/entity/pyro/pyro.png");
    public PyroRenderer(EntityRendererFactory.Context context) {
        super(context, new PyroModel(context.getPart(ModEntityModelLayers.PYRO)), 0.5f);
    }

    @Override
    public PyroEntityRenderState createRenderState() {
        return new PyroEntityRenderState();
    }

    @Override
    public void updateRenderState(PyroEntity livingEntity, PyroEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);

        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.attackAnimationState.copyFrom(livingEntity.attackAnimationState);
        livingEntityRenderState.age = livingEntity.age;
    }

    @Override
    public void render(PyroEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(PyroEntityRenderState state) {
        return TEXTURE;
    }
}
