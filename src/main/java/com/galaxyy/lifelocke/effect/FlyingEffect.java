package com.galaxyy.lifelocke.effect;

import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class FlyingEffect extends StatusEffect {
    protected FlyingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        if (entity instanceof PlayerEntity) {
            ItemStack elytra = new ItemStack(Items.ELYTRA);
            // ((PlayerEntity) entity).sendMessage(Text.literal("" + world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.BINDING_CURSE).isPresent()));
            elytra.addEnchantment(world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.BINDING_CURSE).get(), 1);
            elytra.addEnchantment(world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.VANISHING_CURSE).get(), 1);
            elytra.addEnchantment(world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.UNBREAKING).get(), 65536);
            entity.equipStack(EquipmentSlot.CHEST, elytra);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
