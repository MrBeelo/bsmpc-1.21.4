package net.mrbeelo.bsmpc.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.screen.custom.handler.DeliberatorScreenHandler;

public class DeliberatorScreen extends HandledScreen<DeliberatorScreenHandler> {
    public static final Identifier GUI_TEXTURE = BsmpC.id("textures/gui/deliberator/deliberator_gui.png");

    public DeliberatorScreen(DeliberatorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        //RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR);
        //RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        //RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        int regionWidth = 176;
        int regionHeight = 166;

        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, x, y, 0f, 0f, backgroundWidth, backgroundHeight, regionWidth, regionHeight, 256, 256);
    }

    @Override
    protected void init() {
        super.init();

        //titleY = 1000;
        //playerInventoryTitleY = 1000;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
