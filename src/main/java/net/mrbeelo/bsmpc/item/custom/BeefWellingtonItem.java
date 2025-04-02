package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class BeefWellingtonItem extends Item {
    boolean isRaw = true;
    public BeefWellingtonItem(Settings settings, boolean raw) {
        super(settings);
        this.isRaw = raw;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(!Screen.hasShiftDown()) {
            tooltip.add(Text.literal("Press §eShift§r to learn more!"));
        } else {
            if(isRaw)
            {
                tooltip.add(Text.literal("IT'S RAWWWWW!!!"));
            } else {
                tooltip.add(Text.literal("IT'S OVERCOOKED, OVERDONE, FUCK OFF!!!!"));
            }
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
