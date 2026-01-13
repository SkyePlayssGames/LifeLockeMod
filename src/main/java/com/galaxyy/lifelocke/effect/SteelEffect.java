package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class SteelEffect extends MobEffect {
    protected SteelEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        Level world = entity.level();
        if (entity instanceof Player) {
            Registry<Enchantment> enchantments = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);


            ItemStack boots = new ItemStack(Items.IRON_BOOTS);
            ItemStack leggings = new ItemStack(Items.IRON_LEGGINGS);
            ItemStack chestplate = new ItemStack(Items.IRON_CHESTPLATE);

            Holder<Enchantment> protection = Holder.direct(enchantments.getValue(Enchantments.PROTECTION));
            Holder<Enchantment> binding = Holder.direct(enchantments.getValue(Enchantments.BINDING_CURSE));

            boots.enchant(protection, 5);
            leggings.enchant(protection, 5);
            chestplate.enchant(protection, 5);

            boots.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
            leggings.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
            chestplate.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

            boots.enchant(binding, 1);
            leggings.enchant(binding, 1);
            chestplate.enchant(binding, 1);

            boots.set(ModDataComponents.STEEL, Unit.INSTANCE);
            leggings.set(ModDataComponents.STEEL, Unit.INSTANCE);
            chestplate.set(ModDataComponents.STEEL, Unit.INSTANCE);

            entity.setItemSlot(EquipmentSlot.FEET, boots);
            entity.setItemSlot(EquipmentSlot.LEGS, leggings);
            entity.setItemSlot(EquipmentSlot.CHEST, chestplate);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
