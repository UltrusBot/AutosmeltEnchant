package me.ultrusmods.smeltingtouch.enchantment;

import me.ultrusmods.smeltingtouch.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class SmeltingTouchEffectComponents {
    public static DataComponentType<List<ConditionalEffect<Unit>>> SMELT_DROPS =
            Registry.register(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Constants.id("smelt_drops"),
                DataComponentType.<List<ConditionalEffect<Unit>>>builder()
                    .persistent(ConditionalEffect.codec(Unit.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf())
                    .build()
            );

    public static void register() {
    }
}
