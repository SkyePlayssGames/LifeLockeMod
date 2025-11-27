package com.galaxyy.lifelocke.effect;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Unit;
import net.minecraft.world.World;

public class SteelEffect extends StatusEffect {
    protected SteelEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        World world = entity.getEntityWorld();
        if (entity instanceof PlayerEntity) {
            Registry<Enchantment> enchantments = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);


            ItemStack boots = new ItemStack(Items.IRON_BOOTS);
            ItemStack leggings = new ItemStack(Items.IRON_LEGGINGS);
            ItemStack chestplate = new ItemStack(Items.IRON_CHESTPLATE);

            RegistryEntry<Enchantment> protection = RegistryEntry.of(enchantments.get(Enchantments.PROTECTION));
            RegistryEntry<Enchantment> binding = RegistryEntry.of(enchantments.get(Enchantments.BINDING_CURSE));
            RegistryEntry<Enchantment> vanishing = RegistryEntry.of(enchantments.get(Enchantments.VANISHING_CURSE));

            boots.addEnchantment(protection, 5);
            leggings.addEnchantment(protection, 5);
            chestplate.addEnchantment(protection, 5);

            boots.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
            leggings.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
            chestplate.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);

            boots.addEnchantment(binding, 1);
            leggings.addEnchantment(binding, 1);
            chestplate.addEnchantment(binding, 1);

            boots.addEnchantment(vanishing, 1);
            leggings.addEnchantment(vanishing, 1);
            chestplate.addEnchantment(vanishing, 1);

            entity.equipStack(EquipmentSlot.FEET, boots);
            entity.equipStack(EquipmentSlot.LEGS, leggings);
            entity.equipStack(EquipmentSlot.CHEST, chestplate);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
