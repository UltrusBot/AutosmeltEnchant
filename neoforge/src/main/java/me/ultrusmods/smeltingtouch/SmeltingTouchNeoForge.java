package me.ultrusmods.smeltingtouch;


import me.ultrusmods.smeltingtouch.enchantment.SmeltingTouchEffectComponents;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class SmeltingTouchNeoForge {

    public SmeltingTouchNeoForge(IEventBus eventBus) {
        eventBus.addListener(this::onRegister);
    }

    public void onRegister(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE) {
            SmeltingTouchEffectComponents.register();
        }

    }
}