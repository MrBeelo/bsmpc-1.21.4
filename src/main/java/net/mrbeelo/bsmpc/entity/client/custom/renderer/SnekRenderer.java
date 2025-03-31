package net.mrbeelo.bsmpc.entity.client.custom.renderer;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.client.ModEntityModelLayers;
import net.mrbeelo.bsmpc.entity.client.custom.model.SnekModel;
import net.mrbeelo.bsmpc.entity.client.custom.state.SnekEntityRenderState;
import net.mrbeelo.bsmpc.entity.custom.SnekEntity;
import net.mrbeelo.bsmpc.entity.variant.SnekVariant;

import java.util.Map;

public class SnekRenderer extends MobEntityRenderer<SnekEntity, SnekEntityRenderState, SnekModel> {
    private static final Map<SnekVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SnekVariant.class), map -> {
                map.put(SnekVariant.GREEN, BsmpC.id("textures/entity/snek/snek_green.png"));
                map.put(SnekVariant.RED, BsmpC.id("textures/entity/snek/snek_red.png"));
            });

    public SnekRenderer(EntityRendererFactory.Context context) {
        super(context, new SnekModel(context.getPart(ModEntityModelLayers.SNEK)), 0.5f);
    }

    @Override
    public SnekEntityRenderState createRenderState() {
        return new SnekEntityRenderState();
    }

    @Override
    public void updateRenderState(SnekEntity livingEntity, SnekEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);

        livingEntityRenderState.variant = livingEntity.getVariant();
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.attackAnimationState.copyFrom(livingEntity.attackAnimationState);
        livingEntityRenderState.age = livingEntity.age;
    }

    public static final Identifier TEXTURE = BsmpC.id("textures/entity/snek/snek_green.png");

    @Override
    public Identifier getTexture(SnekEntityRenderState state) {
        //state.getClass()
        return LOCATION_BY_VARIANT.get(state.variant);
    }
}
