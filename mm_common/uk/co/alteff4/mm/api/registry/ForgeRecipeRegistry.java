package uk.co.alteff4.mm.api.registry;

import uk.co.alteff4.mm.api.multiio.SimpleRecipes;
import uk.co.alteff4.mm.api.multiio.recipe.RecipeData;

import net.minecraft.item.ItemStack;

/**
 * 
 * MMO Materials
 * 
 * ForgeRecipeRegistry
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ForgeRecipeRegistry {
    private static SimpleRecipes instance;

    public static SimpleRecipes instance() {
        if (instance == null)
            instance = new SimpleRecipes();
        return instance;
    }

    /**
     * 
     * Adds a recipe to the Forge. Returns true if it overrides an existing one.
     * 
     * @param input
     *            The ItemStack that get's processed
     * @param output
     *            The ItemStack resulting after processing
     * @return true, if the addition was successfull
     */
    public static boolean addRecipe(ItemStack input, ItemStack output,
            int neededHeat) {
        return instance().addRecipe(input, output,
                new RecipeData("neededHeat", neededHeat));
    }

    /**
     * 
     * Get's the result for an given input.
     * 
     * @param input
     *            The ItemStack to get the result for
     * @return the resulting ItemStack for the given input, null if the recipe
     *         doesn't exist
     */
    public static ItemStack getResult(ItemStack input) {
        return instance().getResult(input);
    }

    /**
     * 
     * Checks the given input ItemStack for a result.
     * 
     * @param input
     *            The input ItemStack to check
     * @return true if the input can be processed
     */
    public static boolean canSmelt(ItemStack input) {
        if (getResult(input) != null)
            return true;
        return false;
    }
}
