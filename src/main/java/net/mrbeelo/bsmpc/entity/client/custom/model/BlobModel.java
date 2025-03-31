package net.mrbeelo.bsmpc.entity.client.custom.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.mrbeelo.bsmpc.entity.client.animation.BlobAnimations;
import net.mrbeelo.bsmpc.entity.client.animation.SnekAnimations;
import net.mrbeelo.bsmpc.entity.client.custom.state.BlobEntityRenderState;
import net.mrbeelo.bsmpc.entity.custom.BlobEntity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BlobModel extends EntityModel<BlobEntityRenderState> {
	private final ModelPart main;
	private final ModelPart body;

	public BlobModel(ModelPart root) {
        super(root);
        this.main = root.getChild("main");
		this.body = main.getChild("body");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = main.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -7.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -19.0F, 0.0F));

		ModelPartData leftleg = main.addChild("leftleg", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -10.0F, 0.0F));

		ModelPartData rightleg = main.addChild("rightleg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -10.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(BlobEntityRenderState state) {
		super.setAngles(state);

		this.getMain().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(this.getBody().yaw, this.getBody().pitch);

		this.animateWalking(BlobAnimations.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2.0f, 2.5f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25F, 45f);

		this.body.yaw = headYaw * 0.017453292F;
		this.body.pitch = headPitch * 0.017453292F;
	}

	public ModelPart getMain() {
		return main;
	}

	public ModelPart getBody() {
		return body;
	}
}