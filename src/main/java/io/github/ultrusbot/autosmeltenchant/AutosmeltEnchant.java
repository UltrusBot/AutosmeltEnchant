package io.github.ultrusbot.autosmeltenchant;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AutosmeltEnchant implements ModInitializer {

    public static Enchantment AUTO_SMELT = register(new AutosmeltEnchantment(), new Identifier("autosmeltenchant:auto_smelt"));
    @Override
    public void onInitialize() {
    }
    private static Enchantment register(Enchantment enchant, Identifier id) {
        return Registry.register(Registry.ENCHANTMENT, id, enchant);

    }
}
