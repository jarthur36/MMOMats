package uk.co.alteff4.mm.api.registry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import uk.co.alteff4.mm.api.material.MMOMaterial;
import uk.co.alteff4.mm.api.util.LogHelper;

/**
 * 
 * MMO Materials
 * 
 * MaterialRegistry
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class MaterialRegistry {
    public static final int MATERIAL_ITEM_ID_DEFAULT = 5001;
    public static int MATERIAL_ITEM_ID;
    private static MaterialRegistry instance;
    public HashMap<String, MMOMaterial> materials;

    private MaterialRegistry() {
        materials = new HashMap<String, MMOMaterial>();
    }

    public static MaterialRegistry instance() {
        if (instance == null)
            instance = new MaterialRegistry();
        return instance;
    }

    private static void addMapping(String id, MMOMaterial material) {
        instance().materials.put(id, material);
    }

    /**
     * 
     * Add a new material to the mod.
     * 
     * Be aware: Existing materials will be overriden automatically!
     * 
     * @param id
     *            The ID the material should be assigned to
     * @param material
     *            The material to assign to the given identifier
     * @return false if the ID given by the material is the same as the
     *         parameter, otherwise always true
     */
    public static boolean addMaterial(String id, MMOMaterial material) {
        if (containsMaterial(id))
            LogHelper.log(Level.INFO,
                    "Material ID conflict detected, overwriting existing one.");
        if (!material.getID().equals(id))
            return false;
        addMapping(id, material);
        return true;
    }

    /**
     * 
     * Returns the material for the given ID.
     * 
     * @param id
     *            The ID to search for
     * @return the material for the given ID, null if it doesn't exist
     */
    public static MMOMaterial getMaterial(String id) {
        if (containsMaterial(id))
            return instance().materials.get(id);
        return null;
    }

    /**
     * 
     * Returns an iterator for they keys of the registry.
     * 
     * @return an iterator for the keys of the registry
     */
    public static Iterator<String> getKeyIterator() {
        return instance().materials.keySet().iterator();
    }

    /**
     * 
     * Search the registry for the given ID
     * 
     * @param id
     *            The ID of the material to check
     * @return true if the material with the given ID is registered
     */
    public static boolean containsMaterial(String id) {
        return instance().materials.containsKey(id);
    }

    /**
     * 
     * Creates an ItemStack for the given material.
     * 
     * @param id
     *            The ID of the material to create the ItemStack of.
     * @param amount
     *            The amount of items in that ItemStack.
     * @param tier
     *            The tier of the material to create. Can go from 1 to 6 (lowest
     *            to highest tier).
     * @return
     */
    public static ItemStack createStack(String id, int amount, int tier) {
        if (tier <= 0 || tier > 6)
            return null;
        if (!containsMaterial(id))
            return null;

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("MaterialType", id);
        ItemStack is = new ItemStack(MATERIAL_ITEM_ID, 1, tier - 1);
        is.setTagCompound(tag);

        return is;
    }
}
