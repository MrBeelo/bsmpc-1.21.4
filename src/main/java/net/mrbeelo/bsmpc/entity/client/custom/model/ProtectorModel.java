package net.mrbeelo.bsmpc.entity.client.custom.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import net.mrbeelo.bsmpc.entity.client.animation.ProtectorAnimations;
import net.mrbeelo.bsmpc.entity.client.custom.state.ProtectorBossEntityRenderState;

// Made with Blockbench 4.12.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ProtectorModel extends EntityModel<ProtectorBossEntityRenderState> {
	private final ModelPart main;
	private final ModelPart chest;
	private final ModelPart head;
	public ProtectorModel(ModelPart root) {
        super(root);
        this.main = root.getChild("main");
		this.chest = this.main.getChild("chest");
		this.head = this.chest.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(80, 68).cuboid(-15.0F, -35.0F, -6.0F, 30.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 18.0F, 0.0F));

		ModelPartData rightleg = main.addChild("rightleg", ModelPartBuilder.create().uv(48, 159).cuboid(-3.0F, -3.0F, -5.0F, 10.0F, 21.0F, 10.0F, new Dilation(0.0F)), ModelTransform.origin(-11.0F, -27.0F, 0.0F));

		ModelPartData rightfoot = rightleg.addChild("rightfoot", ModelPartBuilder.create().uv(64, 126).cuboid(-6.0F, -2.0F, -7.0F, 14.0F, 19.0F, 14.0F, new Dilation(0.0F)), ModelTransform.origin(1.0F, 16.0F, 0.0F));

		ModelPartData leftleg = main.addChild("leftleg", ModelPartBuilder.create().uv(88, 159).cuboid(-7.0F, -3.0F, -5.0F, 10.0F, 21.0F, 10.0F, new Dilation(0.0F)), ModelTransform.origin(11.0F, -27.0F, 0.0F));

		ModelPartData leftfoot = leftleg.addChild("leftfoot", ModelPartBuilder.create().uv(120, 126).cuboid(-8.0F, -2.0F, -7.0F, 14.0F, 19.0F, 14.0F, new Dilation(0.0F)), ModelTransform.origin(-1.0F, 16.0F, 0.0F));

		ModelPartData chest = main.addChild("chest", ModelPartBuilder.create().uv(0, 37).cuboid(-17.0F, -15.0F, -6.0F, 34.0F, 16.0F, 15.0F, new Dilation(0.0F))
		.uv(132, 17).cuboid(-17.0F, -15.0F, -9.0F, 11.0F, 16.0F, 3.0F, new Dilation(0.0F))
		.uv(164, 72).cuboid(-6.0F, -15.0F, -9.0F, 11.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(64, 108).cuboid(-6.0F, -11.0F, -9.0F, 2.0F, 5.0F, 3.0F, new Dilation(0.0F))
		.uv(48, 146).cuboid(-4.0F, -11.0F, -9.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(98, 60).cuboid(-6.0F, -1.0F, -9.0F, 23.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(164, 65).cuboid(2.0F, -5.0F, -9.0F, 15.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(186, 41).cuboid(11.0F, -15.0F, -9.0F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F))
		.uv(164, 79).cuboid(5.0F, -15.0F, -9.0F, 6.0F, 5.0F, 3.0F, new Dilation(0.0F))
		.uv(64, 116).cuboid(8.0F, -10.0F, -9.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(150, 60).cuboid(0.0F, -7.0F, -9.0F, 17.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-22.0F, -30.0F, -11.0F, 44.0F, 15.0F, 22.0F, new Dilation(0.0F))
		.uv(98, 37).cuboid(-11.0F, -37.0F, -5.0F, 22.0F, 7.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -36.0F, 0.0F));

		ModelPartData rightarm = chest.addChild("rightarm", ModelPartBuilder.create().uv(144, 88).cuboid(-5.0F, 0.0F, -6.0F, 12.0F, 22.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(-28.0F, -25.0F, 0.0F));

		ModelPartData righthand = rightarm.addChild("righthand", ModelPartBuilder.create().uv(80, 88).cuboid(-7.0F, 0.0F, -8.0F, 16.0F, 22.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 0.0F));

		ModelPartData cube_r1 = righthand.addChild("cube_r1", ModelPartBuilder.create().uv(24, 180).cuboid(-2.0F, -7.0F, -2.0F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 15.0F, 4.0F, 2.1666F, 0.4442F, 1.3437F));

		ModelPartData cube_r2 = righthand.addChild("cube_r2", ModelPartBuilder.create().uv(12, 180).cuboid(-2.0F, -7.0F, -2.0F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 11.0F, 4.0F, 2.1937F, -0.5187F, 0.7181F));

		ModelPartData cube_r3 = righthand.addChild("cube_r3", ModelPartBuilder.create().uv(0, 180).cuboid(-2.0F, -7.0F, -2.0F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 11.0F, 4.0F, -0.0752F, -0.5187F, 0.7181F));

		ModelPartData cube_r4 = righthand.addChild("cube_r4", ModelPartBuilder.create().uv(176, 151).cuboid(-2.0F, -7.0F, -2.0F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 16.0F, -5.0F, -0.5236F, 0.0F, 2.138F));

		ModelPartData cube_r5 = righthand.addChild("cube_r5", ModelPartBuilder.create().uv(176, 122).cuboid(-2.0F, -7.0F, -2.0F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 16.0F, 0.0F, 0.0F, 0.0F, 2.138F));

		ModelPartData leftarm = chest.addChild("leftarm", ModelPartBuilder.create().uv(0, 146).cuboid(-7.0F, 0.0F, -6.0F, 12.0F, 22.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(28.0F, -25.0F, 0.0F));

		ModelPartData cube_r6 = leftarm.addChild("cube_r6", ModelPartBuilder.create().uv(164, 159).cuboid(-2.0F, -33.0F, -2.0F, 3.0F, 34.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 5.0F, 3.0F, -0.4124F, 1.0466F, -0.7381F));

		ModelPartData cube_r7 = leftarm.addChild("cube_r7", ModelPartBuilder.create().uv(174, 17).cuboid(-2.0F, -33.0F, -2.0F, 3.0F, 34.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 5.0F, 3.0F, 0.3268F, 0.4823F, 0.6774F));

		ModelPartData cube_r8 = leftarm.addChild("cube_r8", ModelPartBuilder.create().uv(152, 159).cuboid(-2.0F, -33.0F, -2.0F, 3.0F, 34.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 5.0F, 3.0F, 0.9321F, 0.3049F, 1.1104F));

		ModelPartData cube_r9 = leftarm.addChild("cube_r9", ModelPartBuilder.create().uv(140, 159).cuboid(-2.0F, -33.0F, -2.0F, 3.0F, 34.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -2.0F, 0.2189F, 0.5985F, 0.2832F));

		ModelPartData cube_r10 = leftarm.addChild("cube_r10", ModelPartBuilder.create().uv(128, 159).cuboid(-2.0F, -33.0F, -2.0F, 3.0F, 34.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 5.0F, -0.2359F, -0.422F, 0.5307F));

		ModelPartData lefthand = leftarm.addChild("lefthand", ModelPartBuilder.create().uv(0, 108).cuboid(-9.0F, 0.0F, -8.0F, 16.0F, 22.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 0.0F));

		ModelPartData head = chest.addChild("head", ModelPartBuilder.create().uv(0, 68).cuboid(-10.0F, -17.0F, -10.0F, 20.0F, 20.0F, 20.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -27.0F, -13.0F));

		ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(188, 122).cuboid(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -15.0F, 1.0F, -0.3695F, 0.3272F, -0.5602F));

		ModelPartData cube_r12 = head.addChild("cube_r12", ModelPartBuilder.create().uv(186, 17).cuboid(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -15.0F, -3.0F, 0.6109F, -0.2182F, -0.5236F));

		ModelPartData cube_r13 = head.addChild("cube_r13", ModelPartBuilder.create().uv(184, 180).cuboid(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -14.0F, -3.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData cube_r14 = head.addChild("cube_r14", ModelPartBuilder.create().uv(176, 180).cuboid(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -8.0F, -5.0F, 0.5236F, 0.6109F, 0.4363F));

		ModelPartData cube_r15 = head.addChild("cube_r15", ModelPartBuilder.create().uv(36, 180).cuboid(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -14.0F, 0.0F, 0.3491F, 0.3927F, 0.3927F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(132, 0).cuboid(-12.0F, -5.0F, -11.0F, 24.0F, 7.0F, 10.0F, new Dilation(0.0F))
		.uv(48, 152).cuboid(10.0F, -9.0F, -3.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(174, 54).cuboid(10.0F, -9.0F, -11.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(160, 23).cuboid(3.0F, -9.0F, -11.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(56, 152).cuboid(-12.0F, -9.0F, -11.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(160, 29).cuboid(-12.0F, -9.0F, -3.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(160, 17).cuboid(-3.0F, -9.0F, -11.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(64, 122).cuboid(-10.0F, -7.0F, -11.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(160, 122).cuboid(-5.0F, -7.0F, -11.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(72, 122).cuboid(8.0F, -7.0F, -11.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(144, 122).cuboid(10.0F, -7.0F, -9.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(152, 122).cuboid(-12.0F, -7.0F, -9.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(168, 122).cuboid(-12.0F, -7.0F, -5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 3.0F, -1.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}

	@Override
	public void setAngles(ProtectorBossEntityRenderState state) {
		super.setAngles(state);

		this.getMain().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(this.getHead().yaw, this.getHead().pitch);

		this.animateWalking(ProtectorAnimations.MOVE, state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2.0f, 2.5f);
		this.animate(state.idleAnimationState, ProtectorAnimations.IDLE, state.age, 1f);
		this.animate(state.genericAttackAnimationState, ProtectorAnimations.GENERIC_ATTACK, state.age, 1f);
		this.animate(state.attack1AnimationState, ProtectorAnimations.ATTACK1, state.age, 1f);
		this.animate(state.attack2AnimationState, ProtectorAnimations.ATTACK2, state.age, 1f);
		this.animate(state.attack3AnimationState, ProtectorAnimations.ATTACK3, state.age, 1f);
		this.animate(state.awakeningAnimationState, ProtectorAnimations.AWAKE, state.age, 1f);
		this.animate(state.staticAnimationState, ProtectorAnimations.KNEELING, state.age, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25F, 45f);

		this.main.yaw = headYaw * 0.017453292F;
		this.main.pitch = headPitch * 0.017453292F;
	}

	public ModelPart getMain() {
		return main;
	}

	public ModelPart getHead() {
		return head;
	}
}