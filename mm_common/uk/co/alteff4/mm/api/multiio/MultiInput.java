package uk.co.alteff4.mm.api.multiio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;

/**
 * 
 * MMO Materials
 * 
 * MultiInput
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class MultiInput {

    private boolean shapeless;
    private RecipeInput[] inputs;

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
        ArrayList<RecipeInput> temp = new ArrayList<RecipeInput>();
        for (ItemStack stack : inputs)
            temp.add(new RecipeInput(stack));
        this.inputs = temp.toArray(this.inputs);
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

    /**
     * 
     * Returns an ItemStack array of all the inputs.
     * 
     * @return the ItemStack array of the inputs
     */
    public ItemStack[] getInputStacks() {
        ArrayList<ItemStack> temp = new ArrayList<ItemStack>();
        for (RecipeInput input : inputs)
            temp.add(input.getStack());
        return temp.toArray(new ItemStack[inputs.length]);
    }

    /**
     * 
     * Returns an RecipeInput array of all the inputs. Used for internals.
     * 
     * @return the RecipeInput array of the inputs
     */
    public RecipeInput[] getInputs() {
        return inputs;
    }

    private boolean compareInputs(boolean shapeless, RecipeInput[] inputs1,
            RecipeInput[] inputs2) {
        boolean valid = false;
        if (shapeless) {
            Set<RecipeInput> set1 = new HashSet<RecipeInput>(
                    Arrays.asList(inputs1));
            Set<RecipeInput> set2 = new HashSet<RecipeInput>(
                    Arrays.asList(inputs2));
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

    /**
     * 
     * MMO Materials
     * 
     * RecipeInput
     * 
     * @author PaleoCrafter
     * @license Lesser GNU Public License v3
     *          (http://www.gnu.org/licenses/lgpl.html)
     * 
     */
    private class RecipeInput {
        private ItemStack stack;

        public RecipeInput(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof RecipeInput))
                return false;
            return stack.isItemEqual(((RecipeInput) obj).getStack());
        }

        public ItemStack getStack() {
            return stack;
        }
    }
}
