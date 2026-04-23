package me.ultrusmods.smeltingtouch.mixin;

import me.ultrusmods.smeltingtouch.enchantment.SmeltingRecipeOutputAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SmeltingRecipe.class)
public abstract class SmeltingRecipeMixin extends AbstractCookingRecipe implements SmeltingRecipeOutputAccess {

    public SmeltingRecipeMixin(String group, CookingBookCategory category, Ingredient input, ItemStack result, float experience, int cookingTime) {
        super(group, category, input, result, experience, cookingTime);
    }

    @Override
    public ItemStack getOutputForSmeltingTouch() {
        return  this.result().copy();
    }
}
