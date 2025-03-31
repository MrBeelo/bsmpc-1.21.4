package net.mrbeelo.bsmpc.entity.client.custom.renderer;

import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.client.custom.state.BulletProjectileEntityRenderState;
import net.mrbeelo.bsmpc.entity.custom.BulletProjectileEntity;

public class BulletProjectileRenderer extends ProjectileEntityRenderer<BulletProjectileEntity, BulletProjectileEntityRenderState> {
    public static final Identifier TEXTURE = BsmpC.id("textures/entity/bullet/bullet.png");


    public BulletProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public BulletProjectileEntityRenderState createRenderState() {
        return new BulletProjectileEntityRenderState();
    }

    @Override
    public void updateRenderState(BulletProjectileEntity persistentProjectileEntity, BulletProjectileEntityRenderState projectileEntityRenderState, float f) {
        super.updateRenderState(persistentProjectileEntity, projectileEntityRenderState, f);
    }

    @Override
    protected Identifier getTexture(BulletProjectileEntityRenderState state) {
        return TEXTURE;
    }
}
