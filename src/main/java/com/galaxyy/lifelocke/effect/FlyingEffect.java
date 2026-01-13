package com.galaxyy.lifelocke.effect;

import com.galaxyy.lifelocke.item.data_component.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class FlyingEffect extends MobEffect {
    protected FlyingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        if (entity.isShiftKeyDown()) {
            entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 5, 4, false, false));
        }
        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        Level world = entity.level();
        if (entity instanceof Player) {
            ItemStack elytra = new ItemStack(Items.ELYTRA);
            elytra.enchant(Holder.direct(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getValue(Enchantments.BINDING_CURSE)), 1);
            // elytra.addEnchantment(RegistryEntry.of(world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).get(Enchantments.VANISHING_CURSE)), 1);
            elytra.set(ModDataComponents.FLYING, Unit.INSTANCE);
            elytra.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
            entity.setItemSlot(EquipmentSlot.CHEST, elytra);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
