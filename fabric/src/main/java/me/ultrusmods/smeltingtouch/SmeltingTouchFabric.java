package me.ultrusmods.smeltingtouch;

import me.ultrusmods.smeltingtouch.enchantment.SmeltingTouchEffectComponents;
import net.fabricmc.api.ModInitializer;

public class SmeltingTouchFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        SmeltingTouchEffectComponents.register();
    }
}
