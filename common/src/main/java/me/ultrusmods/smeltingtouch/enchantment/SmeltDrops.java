package me.ultrusmods.smeltingtouch.enchantment;

import com.mojang.serialization.Codec;

public record SmeltDrops() {
    public static final SmeltDrops INSTANCE = new SmeltDrops();
    public static final Codec<SmeltDrops> CODEC = Codec.unit(() -> INSTANCE);
}
