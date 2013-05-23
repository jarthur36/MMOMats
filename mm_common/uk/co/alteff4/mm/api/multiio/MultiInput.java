package uk.co.alteff4.mm.api.multiio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;

public class MultiInput {
	private boolean shapeless;
	private ItemStack[] inputs;

	/**
	 * 
	 * Creates a new instance of the MultiInput class.
	 * 
	 * @param isShapeless
	 *            Defines whether the recipe is shapeless or not.
	 * @param inputs
	 *            The input ItemStacks needed for the recipe. They need to be in
	 *            the right order for a shaped recipe.
	 */
	public MultiInput(boolean shapeless, ItemStack... inputs) {
		this.shapeless = shapeless;
		this.inputs = inputs;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MultiInput) {
			MultiInput input = (MultiInput) obj;
			if (shapeless) {
				if (input.isShapeless())
					if (inputs.length == input.getInputs().length)
						return compareInputs(true, inputs, input.getInputs());
			} else {
				if (!input.isShapeless())
					if (inputs.length == input.getInputs().length)
						return compareInputs(false, inputs, input.getInputs());
			}
		}
		return false;
	}

	/**
	 * 
	 * Checks, if the inputs can be shapeless.
	 * 
	 * @return true, if the recipe is shapeless
	 */
	public boolean isShapeless() {
		return shapeless;
	}

	public ItemStack[] getInputs() {
		return inputs;
	}

	private boolean compareInputs(boolean shapeless, ItemStack[] inputs1,
			ItemStack[] inputs2) {
		boolean valid = false;
		if (shapeless) {
			Set<ItemStack> set1 = new HashSet<ItemStack>(Arrays.asList(inputs1));
			Set<ItemStack> set2 = new HashSet<ItemStack>(Arrays.asList(inputs1));
			return set1.containsAll(set2);
		} else {
			for (int i = 0; i < inputs1.length; i++) {
				if (inputs1[i].equals(inputs2[i]))
					valid = true;
				else
					break;
			}
		}
		return valid;
	}

}
