package net.mrbeelo.bsmpc.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.mrbeelo.bsmpc.BsmpC.scheduleTicks;

public class JetpackItem extends Item {
    public int boostTime = 100;
    public JetpackItem(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(settings.armor(material, type));
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof ServerPlayerEntity player && world instanceof ServerWorld serverWorld) {
            if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof JetpackItem) {
                player.sendMessage(Text.of("Boost left: " + boostTime), true);
                if (player.isSneaking() && !player.isOnGround() && boostTime > 0) {
                    player.setVelocity(player.getVelocity().x, player.getVelocity().y + 0.1, player.getVelocity().z);
                    player.velocityModified = true;
                    boostTime--;
                    player.getEquippedStack(EquipmentSlot.CHEST).damage(1, serverWorld, player, item ->
                            Objects.requireNonNull(player).sendEquipmentBreakStatus(stack.getItem(), EquipmentSlot.MAINHAND));
                    player.getInventory().markDirty();
                } else if (boostTime < 100) {
                    boostTime++;
                } else {
                    boostTime = 100;
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }
}
