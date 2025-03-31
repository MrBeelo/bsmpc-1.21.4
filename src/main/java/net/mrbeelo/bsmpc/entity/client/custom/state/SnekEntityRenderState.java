package net.mrbeelo.bsmpc.entity.client.custom.state;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.mrbeelo.bsmpc.entity.custom.SnekEntity;
import net.mrbeelo.bsmpc.entity.variant.SnekVariant;

public class SnekEntityRenderState extends LivingEntityRenderState {
    public SnekVariant variant;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
}
