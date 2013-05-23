package uk.co.alteff4.mm.api.multiio;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class MultiInputRecipes {
	private HashMap<MultiInput, ItemStack> recipes;
	private int inputAmount;
	private boolean shapeless;

	/**
	 * 
	 * Creates a new instance of the MultiInputRecipes class.
	 * 
	 * @param inputAmount
	 *            Determines, how many ItemStacks are used in all the recipes.
	 * @param shapeless
	 *            Determines, whether all the recipes are shapeless.
	 */
	public MultiInputRecipes(int inputAmount, boolean shapeless) {
		this.inputAmount = inputAmount;
		this.shapeless = shapeless;

		recipes = new HashMap<MultiInput, ItemStack>();
	}

	/**
	 * 
	 * @param output
	 * @param inputs
	 * @return
	 */
	public boolean addRecipe(ItemStack output, ItemStack... inputs) {
		boolean success = false;
		if (!(inputs.length < inputAmount || inputs.length > inputAmount)) {
			recipes.put(new MultiInput(shapeless, inputs), output);
		}
		return success;
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 */
	public ItemStack getResult(ItemStack... inputs) {
		MultiInput key = new MultiInput(shapeless, inputs);
		if (recipes.containsKey(key)) {
			return recipes.get(key);
		}
		return null;
	}
	
	public boolean isRecipeValid(ItemStack... inputs) {
		return getResult(inputs) != null;
	}
}
