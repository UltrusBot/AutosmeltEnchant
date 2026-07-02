package me.ultrusmods.smeltingtouch.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.ultrusmods.smeltingtouch.enchantment.SmeltingRecipeOutputAccess;
import me.ultrusmods.smeltingtouch.enchantment.SmeltingTouchEffectComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public class BlockMixin {

    @ModifyExpressionValue(
            method = "dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemInstance;)Ljava/util/List;")
    )
    private static List<ItemStack> smeltDroppedStacks(List<ItemStack> original, BlockState state, Level level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, ItemStack tool) {
        if (breaker == null || !(level instanceof ServerLevel serverLevel)) {
            return original;
        }

        List<ItemStack> drops = new ArrayList<>();


        if (!EnchantmentHelper.has(tool, SmeltingTouchEffectComponents.SMELT_DROPS)) {
            return original;
        }

        for (ItemStack drop : original) {
            var recipe = serverLevel.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(drop), serverLevel);

            if (recipe.isPresent() && recipe.get().value() instanceof SmeltingRecipeOutputAccess smeltingRecipeOutputAccess) {
                ItemStack smelted = smeltingRecipeOutputAccess.getOutputForSmeltingTouch();
                smelted.setCount(drop.getCount());
                drops.add(smelted);
            } else {
                drops.add(drop);
            }
        }

        return drops;
    }
}
