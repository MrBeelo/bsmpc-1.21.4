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
}
