package com.galaxyy.lifelocke.itemgroup;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.block.ModBlocks;
import com.galaxyy.lifelocke.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup LIFELOCKE_STUFF = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LifeLocke.MOD_ID, "lifelocke_stuff"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(Blocks.BARRIER))
                    .displayName(Text.translatable("itemgroup.lifelocke.lifelocke_stuff"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.DUMMY_ITEM);
                        entries.add(ModBlocks.DUMMY_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        System.out.println("Registering Item Groups for " + LifeLocke.MOD_ID);
    }
}
