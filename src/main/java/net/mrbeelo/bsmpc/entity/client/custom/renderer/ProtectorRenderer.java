package net.mrbeelo.bsmpc.entity.client.custom.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.client.ModEntityModelLayers;
import net.mrbeelo.bsmpc.entity.client.custom.model.ProtectorModel;
import net.mrbeelo.bsmpc.entity.client.custom.state.ProtectorBossEntityRenderState;
import net.mrbeelo.bsmpc.entity.custom.ProtectorBossEntity;

public class ProtectorRenderer extends MobEntityRenderer<ProtectorBossEntity, ProtectorBossEntityRenderState, ProtectorModel> {
    public ProtectorRenderer(EntityRendererFactory.Context context) {
        super(context, new ProtectorModel(context.getPart(ModEntityModelLayers.PROTECTOR)), 0.5f);
    }

    public static final Identifier TEXTURE = BsmpC.id("textures/entity/protector/protector.png");

    @Override
    public Identifier getTexture(ProtectorBossEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public ProtectorBossEntityRenderState createRenderState() {
        return new ProtectorBossEntityRenderState();
    }

    @Override
    public void updateRenderState(ProtectorBossEntity livingEntity, ProtectorBossEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);

        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.genericAttackAnimationState.copyFrom(livingEntity.genericAttackAnimationState);
        livingEntityRenderState.attack1AnimationState.copyFrom(livingEntity.attack1AnimationState);
        livingEntityRenderState.attack2AnimationState.copyFrom(livingEntity.attack2AnimationState);
        livingEntityRenderState.attack3AnimationState.copyFrom(livingEntity.attack3AnimationState);
        livingEntityRenderState.awakeningAnimationState.copyFrom(livingEntity.awakeningAnimationState);
        livingEntityRenderState.staticAnimationState.copyFrom(livingEntity.staticAnimationState);
        livingEntityRenderState.age = livingEntity.age;
    }
}
