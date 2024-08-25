package me.ultrusmods.smeltingtouch.mixin;

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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(
            method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void smeltDroppedStacks(BlockState state, ServerLevel level, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> drops = new ArrayList<>();
        List<ItemStack> originalDrops = cir.getReturnValue();

        if (!EnchantmentHelper.has(tool, SmeltingTouchEffectComponents.SMELT_DROPS)) {
            cir.setReturnValue(originalDrops);
            return;
        }

        for (ItemStack drop : originalDrops) {
            var recipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(drop), level);

            if (recipe.isPresent()) {
                ItemStack smelted = recipe.get().value().getResultItem(level.registryAccess()).copy();
                smelted.setCount(drop.getCount());
                drops.add(smelted);
            } else {
                drops.add(drop);
            }
        }

        cir.setReturnValue(drops);
    }
}
