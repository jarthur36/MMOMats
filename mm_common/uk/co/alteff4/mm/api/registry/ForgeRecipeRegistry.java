package uk.co.alteff4.mm.api.registry;

import java.util.HashMap;

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
    public HashMap<ItemStack, ItemStack> recipes;
    private static ForgeRecipeRegistry instance;

    private ForgeRecipeRegistry() {
        recipes = new HashMap<ItemStack, ItemStack>();
    }

    public static ForgeRecipeRegistry instance() {
        if (instance == null)
            instance = new ForgeRecipeRegistry();
        return instance;
    }

    private static void addMapping(ItemStack input, ItemStack output) {
        instance().recipes.put(input, output);
    }

    /**
     * 
     * Adds a recipe to the Forge. Returns true if it overrides an existing one.
     * 
     * @param input
     *            The ItemStack that get's processed
     * @param output
     *            The ItemStack resulting after processing
     * @return true, if it has overridden an existing recipe, otherwise false
     */
    public static boolean addRecipe(ItemStack input, ItemStack output) {
        boolean returnValue = false;
        if (instance().recipes.containsKey(input))
            returnValue = true;
        addMapping(input, output);
        return returnValue;
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
        if (instance().recipes.containsKey(input)) {
            return instance().recipes.get(input);
        }
        return null;
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
