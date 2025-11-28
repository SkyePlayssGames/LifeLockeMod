package com.galaxyy.lifelocke.effect;

import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.World;

public class FlyingEffect extends StatusEffect {
    protected FlyingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity.isSneaking()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 5, 4, false, false));
        }
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        World world = entity.getEntityWorld();
        if (entity instanceof PlayerEntity) {
            ItemStack elytra = new ItemStack(Items.ELYTRA);
            elytra.addEnchantment(RegistryEntry.of(world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).get(Enchantments.BINDING_CURSE)), 1);
            elytra.addEnchantment(RegistryEntry.of(world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).get(Enchantments.VANISHING_CURSE)), 1);
            elytra.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
            entity.equipStack(EquipmentSlot.CHEST, elytra);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
