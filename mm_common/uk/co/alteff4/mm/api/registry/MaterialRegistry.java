package uk.co.alteff4.mm.api.registry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

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
}
